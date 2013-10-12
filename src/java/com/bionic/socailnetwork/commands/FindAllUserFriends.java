package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.FriendsDAO;
import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socialnetwork.daofactory.DAOFactory;
import com.bionic.socailnetwork.entity.Friends;
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
public class FindAllUserFriends implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //request.getSession(true).setAttribute("login", login);
             
        FriendsDAO friendDao = new FriendsDAO();
        String login = (String)request.getSession(true).getAttribute("login");
        String password = request.getParameter("password");
        String page = "";
        
        UserDAO userDao = new DAOFactory().getUserDAO();
        FriendsDAO friendsDao = new DAOFactory().getFriendsDAO();
        
        Users currentUser=userDao.findUserByEmail(login);
        List<Users> friendsList = friendDao.findAllFriendsForUser(currentUser);
        request.setAttribute("list", friendsList);
        //TODO exception for null (not found)
        
        return "/jsp/friends.jspx";
    }
}
