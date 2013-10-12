package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BlockUser implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = "";
        String login = request.getParameter("userlogin");
        
        if (login != null && !login.isEmpty()) {
            UserDAO userDao = new UserDAO();

            Users currentUser = userDao.findUserByEmail(login);
            userDao.changeEnable(currentUser, !currentUser.getEnable());
            request.setAttribute("user", currentUser);
            page = "/jsp/blockUsers.jspx";
        } else {
            page = "/index.jsp";
        }
        return page;
    }
}
