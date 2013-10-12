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
public class Login implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        String login = "";
        String password = "";
        String page = "";
        HttpSession session = request.getSession(true);
        if (session.getAttribute("auth") == null || (Boolean) session.getAttribute("auth").equals(false)) {
            login = request.getParameter("login");
            password = request.getParameter("password");
            session.setAttribute("login", login);
            Users currentUser = userDao.findUserByEmail(login);
            
            if (currentUser.getId() == -1) {
                request.setAttribute("message", "ERROR!\nno such user - " + login);
                page = "/jsp/error.jsp";
            } else if (currentUser.getPassword().equals(password)) {
                if (currentUser.getEnable()) {
                    request.setAttribute("locale","ru");
                    page = "/jsp/profile.jspx";
                    session.setAttribute("auth", true);

                    if (userDao.isUserAdmin(currentUser)) {
                        session.setAttribute("role", "admin");
                    } else {
                        session.setAttribute("role", "user");
                    }
                    request.setAttribute("user", currentUser);
                } else {
                    request.setAttribute("message", "ERROR!\nuser - " + login + " is blocked by admin");
                    page = "/jsp/error.jsp";
                }


            } else {
                request.setAttribute("message", "incorrect password for user " + login
                        + "\nauth is " + session.getAttribute("auth"));
                page = "/jsp/error.jsp";
            }
        } else {
            login = (String) session.getAttribute("login");
            Users currentUser = userDao.findUserByEmail(login);
            request.setAttribute("user", currentUser);
            page = "/jsp/profile.jspx";

        }
        // return "/jsp/test.jsp";
        return page;
    }
}
