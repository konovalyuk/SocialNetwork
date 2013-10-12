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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Катерина
 */
public class AddNewUser implements ICommand{
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException,IOException{
        
        String regexp="^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
        //System.out.println(mail.matches(regexp));
        String page="";
        String login = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String city = request.getParameter("city");
        String password = request.getParameter("password");
        UserDAO userDao = new UserDAO();
        Users currentUser = new Users(1, name, surname, city, true, login, password);
        HttpSession session = request.getSession(true);
      
        if(login!=null&&!login.isEmpty()&&login.matches(regexp)&&!userDao.isUserExist(login)){
            userDao.addUser(currentUser);
//            session.setAttribute("login", login);
//            session.setAttribute("auth", true);
            session.invalidate();
            page="/index.jsp";
        }else{
            request.setAttribute("message", "bad email or existing user, login - "+login);
            page="/jsp/error.jsp";
        }
        
        return page;
    }
    
}
