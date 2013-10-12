/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Катерина
 */
public class CommitChangesProfile implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = (String) request.getSession(false).getAttribute("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String city = request.getParameter("city");
        String password = request.getParameter("password");
    
        InputStream inpSt = null;
        Part filePart = request.getPart("file");
        if (filePart.getContentType().contains("image")) {
            inpSt = filePart.getInputStream();
        }

        UserDAO userDao = new UserDAO();
        Users currentUser = userDao.findUserByEmail(login);
        Integer id = currentUser.getId();
        if (name == null || name.isEmpty()) {
            name = currentUser.getName();
        }
        if (surname == null || surname.isEmpty()) {
            surname = currentUser.getSurname();
        }
        if (city == null || city.isEmpty()) {
            city = currentUser.getCity();
        }
        if (password == null || password.isEmpty()) {
            password = currentUser.getPassword();
        }

        userDao.updateUserInfo(id, name, surname, city, password);
        
        if (inpSt != null && inpSt.available() > 0) {
            userDao.updateImg(id, inpSt);
        }
        
        currentUser = userDao.findUserByEmail(login);
        request.setAttribute("user", currentUser);
        return "/jsp/profile.jspx";
    }
}
