/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.FriendsDAO;
import com.bionic.socialnetwork.dao.FriendsRequestDAO;
import com.bionic.socialnetwork.dao.RequestStatusDictionaryDAO;
import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.Friends;
import com.bionic.socailnetwork.entity.FriendsRequest;
import com.bionic.socailnetwork.entity.RequestStatusDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Катерина
 */
public class ConfirmFriend implements ICommand{
     public String execute(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException,IOException{
         
        String page = "";
        String loginRequesting = request.getParameter("userlogin");
        String loginConfirming = (String) request.getSession(false).getAttribute("login");
        Integer idFriendRequest = Integer.parseInt(request.getParameter("friendRequestId"));
        
        FriendsRequestDAO friendRequestDao = new FriendsRequestDAO();
        FriendsRequest friendRequest = friendRequestDao.findFriendsRequestByID(idFriendRequest);
        
        RequestStatusDictionary status = new RequestStatusDictionary();
        RequestStatusDictionaryDAO requestStatusDao = new RequestStatusDictionaryDAO();
        status = requestStatusDao.findRequestStatusByDescription("confirmed");
        friendRequestDao.changeFriendsRequestStatus(friendRequest, status);
        
        UserDAO userDao = new UserDAO();
        Users userRequesting = userDao.findUserByEmail(loginRequesting);
        Users userConfirming = userDao.findUserByEmail(loginConfirming);

        
        FriendsDAO friendsDao = new FriendsDAO();
        Friends friends = new Friends(1, userRequesting.getId(), userConfirming.getId(), 1);
     
        if (!friendsDao.isUsersFreinds(userConfirming.getId(), userRequesting.getId()) 
                && !loginConfirming.equals(loginRequesting)) {
            friendsDao.addFriend(friends);
        }
        request.setAttribute("user", userRequesting);
        page = "/jsp/addfriend.jspx";
        return page;
     }
}
