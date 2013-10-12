package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.Friends;
import com.bionic.socailnetwork.entity.RelationsDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IFriendsDAO {
    
    List<Friends> findAllFriends();
    List<Users> findAllFriendsForUser(Users user);
    List<Friends> findAllFriendshipsForUser(Users user);
    
    Friends findFriendshipByID(Integer idFriends);
    boolean isUsersFreinds(Users firstUser, Users secondUser);
    
    void addFriendship(Friends friendship);
    void deleteFriendship(Friends friendship);
    
    void changeFriendsRelations(Friends friend, RelationsDictionary relation);
}
