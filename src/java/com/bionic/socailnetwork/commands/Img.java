/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socailnetwork.entity.Users;
import com.bionic.socialnetwork.dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class Img implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        String login = (String) request.getSession(false).getAttribute("login");
        Users currentUser = userDao.findUserByEmail(login);

        byte[] img = currentUser.getPhoto();
        response.setContentType("image/jpg");
  //      response.setContentLength(img.length);
        response.getOutputStream().write(img);
        response.getOutputStream().flush();
        response.getOutputStream().close();
        return "";
    }
}
