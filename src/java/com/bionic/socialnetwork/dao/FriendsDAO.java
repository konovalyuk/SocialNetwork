package com.bionic.socialnetwork.dao;

import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.Friends;
import com.bionic.socailnetwork.entity.RelationsDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Katerina Gluschenko
 */
public class FriendsDAO {
    //id, id_first_user, id_second_user, relations

    private static final int ID_COLUMN = 1;
    private static final int ID_FIRST_USER_COLUMN = 2;
    private static final int ID_SECOND_USER_COLUMN = 3;
    private static final int RELATIONS_COLUMN = 4;
    
    private static final String UNIVERSE_SELECT = "SELECT * FROM friends ";
    private static final String UNIVERSE_UPDATE =
            "UPDATE friends SET id_first_user = %d, id_second_user = %d, "
            + " relations = %d, WHERE id = %d";
    private static final String UPDATE_FRIENDS_RELATIONS = "UPDATE friens SET relations = %d "
            + " WHERE id = %d";

    /*UPDATE table_name
     SET column1=value1,column2=value2,...
     WHERE some_column=some_value;*/
    private static final String UNIVERSE_INSERT =
            "INSERT INTO friends VALUES (%d, %d, %d, %d)";
    //INSERT INTO table_name (column1,column2,column3,...)
    //VALUES (value1,value2,value3,...);
    private static final String UNIVERSE_DELETE = "DELETE FROM friends WHERE id = %d";

   public List<Friends> findAllFriends() {
        return select(UNIVERSE_SELECT);
    }

    public List<Friends> findAllFriendshipsForUser(Users user) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE id_first_user = " + user.getId()
                + " OR id_second_user = " + user.getId();
        return select(sqlSelect);
    }
    
    public List<Users> findAllFriendsForUser(Users user) {
        List<Friends> friendList = findAllFriendshipsForUser(user);
        List<Users> usersFriendsList = new ArrayList<Users>();
        Users tempUser = new Users();
        int id=0;
        for(Friends friend:friendList){
            if(friend.getIdFirstUser().equals(user.getId())){
                id=friend.getIdSecondUser();
                tempUser = new UserDAO().findUserByID(id);
                
            }else if(friend.getIdSecondUser().equals(user.getId())){
                id=friend.getIdFirstUser();
                tempUser = new UserDAO().findUserByID(id);
            }
            usersFriendsList.add(tempUser);
        }
        
        return usersFriendsList;
    }
    
    public Friends findFriendshipByID(Integer idFriends) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE id = " + idFriends;
        //TODO проверку
        return select(sqlSelect).get(0);
    }

    public boolean isUsersFreinds(Integer firstUserId, Integer secondUserId) {
        
        String sqlSelect = UNIVERSE_SELECT + " WHERE id_first_user = " + firstUserId
                + " AND id_second_user = " + secondUserId
                + " OR id_first_user = " + secondUserId
                + " AND id_second_user = " + firstUserId;
        
        return !select(sqlSelect).isEmpty();
    }

    public void addFriend(Friends friendship) {
        if(!isUsersFreinds(friendship.getIdFirstUser(), friendship.getIdSecondUser())){
        String updateQuery = String.format(UNIVERSE_INSERT, generateID(),
                friendship.getIdFirstUser(),
                friendship.getIdSecondUser(), friendship.getRelations());
        update(updateQuery);
        }else{
            System.out.println("already friends");
        }
        
    }

    public void deleteFriendship(Friends friendShip) {
        String deleteQuery = String.format(UNIVERSE_DELETE, friendShip.getId());
        update(deleteQuery);
    }

    public void changeFriendsRelations(Friends friendShip, RelationsDictionary relation) {
        String updateQuery = String.format(UPDATE_FRIENDS_RELATIONS, relation.getId(),
                friendShip.getId());
        update(updateQuery);
    }

    private void update(String updatingQuery) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            statement.executeUpdate(updatingQuery);
        } catch (SQLException e) {
            System.err.print("err in updating info " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.print("err in close updating info " + e);
            }
        }
    }

    private List<Friends> select(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Friends> friendsList = new ArrayList<Friends>();

        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {
                Friends friendship = new Friends();

                friendship.setId(resultSet.getInt(ID_COLUMN));
                friendship.setIdFirstUser(resultSet.getInt(ID_FIRST_USER_COLUMN));
                friendship.setIdSecondUser(resultSet.getInt(ID_SECOND_USER_COLUMN));
                friendship.setRelations(resultSet.getInt(RELATIONS_COLUMN));
                
                friendsList.add(friendship);
            }

        } catch (SQLException e) {
            System.err.print("err in select " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.print("err in close select " + e);
            }
        }

        return friendsList;
    }
    
    public Integer generateID(){
        //String selectQuery = UNIVERSE_SELECT+"WHERE id IN (SELECT max(id) from friends)";
        String sqlSelect = "SELECT max(id) from friends";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int maxId=0;
        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next())
                 maxId = resultSet.getInt(1);
            
        } catch (SQLException e) {
            System.err.print("err in select " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.print("err in close select " + e);
            }
        }

        return ++maxId;
    }
}
