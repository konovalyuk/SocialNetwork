/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.FriendsRequestDAO;
import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.FriendsRequest;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Катерина
 */
public class ShowRequests implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FriendsRequestDAO friendRequestDao = new FriendsRequestDAO();
        
        UserDAO userDao = new UserDAO();
        String confirmingLogin = (String) request.getSession(false).getAttribute("login");
        Users confirmingUser = userDao.findUserByEmail(confirmingLogin);
        List<Users> listUsers = new ArrayList<Users>();
        Users tempUser = new Users();
        
        List<FriendsRequest> friendRequestList = friendRequestDao.findAllUnconfirmedFriendsRequestForUser(confirmingUser);
        HashMap <Integer, Users> list = new HashMap<Integer, Users>();
        for(FriendsRequest friendsRequest: friendRequestList){
            tempUser = userDao.findUserByID(friendsRequest.getIdUserRequesting());
            list.put(friendsRequest.getId(), tempUser);
         //   listUsers.add(tempUser);
        }
        request.setAttribute("list", list);
        return "/jsp/friendsRequest.jspx";
    }
}
