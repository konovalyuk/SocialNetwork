/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socialnetwork.daofactory;

import com.bionic.socialnetwork.dao.FriendsDAO;
import com.bionic.socialnetwork.dao.FriendsRequestDAO;
import com.bionic.socialnetwork.dao.MessageStatusDictionaryDAO;
import com.bionic.socialnetwork.dao.MessagesDAO;
import com.bionic.socialnetwork.dao.RelationsDictionaryDAO;
import com.bionic.socialnetwork.dao.RequestStatusDictionaryDAO;
import com.bionic.socialnetwork.dao.RoleDictionaryDAO;
import com.bionic.socialnetwork.dao.UserDAO;

/**
 *
 * @author Катерина
 */
public class DAOFactory {
   public FriendsDAO getFriendsDAO(){
       return new FriendsDAO();
   }
   
   public FriendsRequestDAO getFriendsRequestDAO(){
       return new FriendsRequestDAO();
   }
   
   public MessageStatusDictionaryDAO getMessageStatusDictionaryDAO(){
       return new MessageStatusDictionaryDAO();
   }
   
   public MessagesDAO getMessagesDAO(){
       return new MessagesDAO();
   } 
   
   public RelationsDictionaryDAO getRelationsDictionaryDAO(){
       return new RelationsDictionaryDAO();
   }
   
   public RequestStatusDictionaryDAO getRequestStatusDictionaryDAO(){
       return new RequestStatusDictionaryDAO();
   }
   
   public RoleDictionaryDAO getRoleDictionaryDAO(){
       return new RoleDictionaryDAO();
   }
   
   public UserDAO getUserDAO(){
       return new UserDAO();
   }
   
}
