package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.MessagesDAO;
import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Messages;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Катерина
 */
public class SendMessage implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recieverLogin = request.getParameter("reciever");
        String senderLogin = (String) request.getSession(false).getAttribute("login");

        UserDAO userDao = new UserDAO();
        Users reciever = userDao.findUserByEmail(recieverLogin);
        Users senderUser = userDao.findUserByEmail(senderLogin);

        String textMessage = request.getParameter("textMessage");

//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date();
//       // String currentDate = dateFormat.format(date);
        Messages message = new Messages(1,textMessage, reciever.getId(), senderUser.getId());
        MessagesDAO messagesDao = new MessagesDAO();

       messagesDao.addMessage(message);
//        request.setAttribute("message", "this msg - "+textMessage+" reciever "+recieverLogin+" sender "+senderLogin);
//        return "/jsp/error.jsp";
        return "/jsp/viewAllMessages.jspx";
    }
}
