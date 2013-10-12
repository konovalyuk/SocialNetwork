/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Катерина
 */
public class Search implements ICommand{
     public String execute(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException,IOException{
         String name = request.getParameter("name");
         String surname = request.getParameter("surname");
         String city = request.getParameter("city");
         
         UserDAO usersDao = new UserDAO();
         
         List<Users> userList = usersDao.findAllUsersByAnyParameter(name, surname, city);
         request.setAttribute("list", userList);
         return"/jsp/search.jspx";
     }

    
}
