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
public class ChangeProfile implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = (String)request.getSession(false).getAttribute("login");
        UserDAO userDao = new UserDAO();
        Users currentUser = userDao.findUserByEmail(login);
        request.setAttribute("user", currentUser);
        return "/jsp/changeProfile.jspx";
    }
}