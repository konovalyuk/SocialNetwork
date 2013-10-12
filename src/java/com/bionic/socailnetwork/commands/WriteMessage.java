/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Катерина
 */
public class WriteMessage implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recieverLogin = request.getParameter("reciever");
        UserDAO userDao = new UserDAO();
        Users reciever = userDao.findUserByEmail(recieverLogin);
       
        request.setAttribute("user", reciever);
        
        request.setAttribute("reciever", recieverLogin);
        return "/jsp/writeMessage.jspx";
    }

}
