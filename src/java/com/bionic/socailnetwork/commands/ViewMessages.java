/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.MessagesDAO;
import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Messages;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Катерина
 */
public class ViewMessages implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = (String) request.getSession(false).getAttribute("login");
        UserDAO userDao = new UserDAO();
        Users currentUser = userDao.findUserByEmail(login);
        MessagesDAO messageDao = new MessagesDAO();

        List<Messages> listOfMessages = messageDao.findAllRecievedMessagesForUser(currentUser);
        HashMap<Messages,Users> list = new HashMap<Messages, Users>();
        for(Messages message:listOfMessages){
            list.put(message, userDao.findUserByID(message.getIdUserSender()));
        }
        
        List<Messages> sentMessages = messageDao.findAllSendMessagesForUser(currentUser);
        HashMap<Messages,Users> listSent = new HashMap<Messages, Users>();
        for(Messages message:sentMessages){
            listSent.put(message, userDao.findUserByID(message.getIdUserReciever()));
        }

//       messageDao.
        request.setAttribute("list",list);
        request.setAttribute("sent",listSent);
        
        return "/jsp/viewAllMessages.jspx";
    }
}
