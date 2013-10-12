/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Users;
import com.bionic.socialnetwork.locale.Localisation;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Катерина
 */
public class ShowProfile implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
       String page = "";
        if (session != null && session.getAttribute("auth").equals(true)) {
            String login = (String) session.getAttribute("login");
            UserDAO userDao = new UserDAO();

            Users currentUser = userDao.findUserByEmail(login);
            request.setAttribute("user", currentUser);
            page = "/jsp/profile.jspx";
        } else {
            page = "/index.jsp";
        }
        return page;
    }
}
