package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.MessageStatusDictionary;
import com.bionic.socailnetwork.entity.Messages;
import com.bionic.socailnetwork.entity.Users;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IMessagesDAO {
    List<Messages> findAllSendMessagesForUser(Users user);
    List<Messages> findAllRecievedMessagesForUser(Users user);
    Messages findMessageById(Integer id);
    
    List<Messages> findAllMessagesForTwoUsers(Users sender, Users reciever);
 
    void addMessage(Messages message);
    void deleteMessage(Messages message);
    
    void changeMessageStatuse(Messages message, MessageStatusDictionary status);
}
