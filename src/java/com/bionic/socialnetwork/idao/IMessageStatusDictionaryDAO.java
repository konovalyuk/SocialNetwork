/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.MessageStatusDictionary;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IMessageStatusDictionaryDAO {
    List<MessageStatusDictionary> findAll();
    MessageStatusDictionary findMessageStatusById(Integer id);
    void addMessageStatus(MessageStatusDictionary status);
    void deleteMessageStatus(MessageStatusDictionary status);
    
}
