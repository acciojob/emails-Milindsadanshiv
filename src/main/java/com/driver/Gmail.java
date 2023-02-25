package com.driver;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity;
    //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }
    ArrayList<myMail> Inbox=new ArrayList<>();
    ArrayList<myMail> Trash=new ArrayList<>();

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        myMail mail = new myMail(date, sender, message);
        if(Inbox.size()<inboxCapacity) {
            Inbox.add(mail);
        }
        else {
            Trash.add(Inbox.get(0));
            Inbox.remove(0);
            Inbox.add(mail);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for (int i=0;i<Inbox.size();i++)
        {
            if (Inbox.get(i).getMessage().equals(message))
            {
                Trash.add(Inbox.get(i));
                Inbox.remove(i);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

          if (Inbox.size()==0)
          {
              return null;
          }
          int a=Inbox.size()-1;
          return Inbox.get(a).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if (Inbox.size()==0)
        {
            return null;
        }
        return Inbox.get(0).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
         for (int i=0;i<Inbox.size();i++)
         {
             if (Inbox.get(i).getDate().after(start) && Inbox.get(i).getDate().before(end))
             {
                 count++;
             }
             if (Inbox.get(i).getDate().equals(start) || Inbox.get(i).getDate().equals(end))
             {
                 count++;
             }
         }
         return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
          return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
          Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
