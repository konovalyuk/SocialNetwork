package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.FriendsRequest;
import com.bionic.socailnetwork.entity.RequestStatusDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IFriendsRequestDAO {
    List<FriendsRequest> findAllFriendsRequest();
    List<FriendsRequest> findAllSendFriendsRequestForUser(Users user);
    List<FriendsRequest> findAllRecievedFriendsRequestForUser(Users user);
    List<FriendsRequest> findAllUnconfirmedFriendsRequestForUser(Users user);
    
    FriendsRequest findFriendsRequestByID(Integer idFriendRequest);
    
    boolean hasUsersFriendsRequest(Users firstUser, Users secondUser);
    
    void changeFriendsRequestStatus(FriendsRequest friendRequest, RequestStatusDictionary status);
    void addFriendsRequest(FriendsRequest friendRequest);
    void deleteFriendsRequest(FriendsRequest fiendRequest);
}
