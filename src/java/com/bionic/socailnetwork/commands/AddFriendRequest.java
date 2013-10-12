package com.bionic.socailnetwork.commands;

import com.bionic.socialnetwork.dao.FriendsDAO;
import com.bionic.socialnetwork.dao.FriendsRequestDAO;
import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socailnetwork.entity.FriendsRequest;
import com.bionic.socailnetwork.entity.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFriendRequest implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = "";

        String loginConfirming = request.getParameter("userlogin");
        String loginRequesting = (String) request.getSession(false).getAttribute("login");
        UserDAO userDao = new UserDAO();

        Users userRequesting = userDao.findUserByEmail(loginRequesting);
        Users userConfirming = userDao.findUserByEmail(loginConfirming);

        //if (login != null && !login.isEmpty()) {

        FriendsRequest friendRequest = new FriendsRequest(1, userRequesting.getId(), userConfirming.getId(), 1);

        FriendsRequestDAO friendRequestDao = new FriendsRequestDAO();
        FriendsDAO friendsDao = new FriendsDAO();
        if (!friendRequestDao.hasUsersFriendsRequest(userRequesting, userConfirming)
              && !friendsDao.isUsersFreinds(userConfirming.getId(), userRequesting.getId()) 
              && !loginConfirming.equals(loginRequesting)) {
            friendRequestDao.addFriendsRequest(friendRequest);
        }
        request.setAttribute("user", userRequesting);
        page = "/jsp/addfriend.jspx";
        return page;
    }
}
