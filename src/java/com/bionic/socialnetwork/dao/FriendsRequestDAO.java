package com.bionic.socialnetwork.dao;

import com.bionic.socialnetwork.idao.IFriendsRequestDAO;
import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.FriendsRequest;
import com.bionic.socailnetwork.entity.RequestStatusDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Катерина
 */
public class FriendsRequestDAO implements IFriendsRequestDAO {
    //id, id_user_requesting, id_user_confirming, request_status

    private static final int ID_COLUMN = 1;
    private static final int ID_USER_REQUESTING_COLUMN = 2;
    private static final int ID_USER_CONFIRMING_COLUMN = 3;
    private static final int REQUEST_STATUS_COLUMN = 4;
    private static final String UNIVERSE_SELECT = "SELECT * FROM friends_request ";
    private static final String UPDATE_FRIENDS_REQUEST_STATUS =
            "UPDATE friends_request SET request_status = %d WHERE id = %d";
    private static final String UNIVERSE_INSERT =
            "INSERT INTO friends_request (id_user_requesting, id_user_confirming, request_status) "
            + "VALUES (%d, %d, %d)";
    private static final String UNIVERSE_DELETE = "DELETE FROM friends_request WHERE id = %d";
    // корректно ли доставать из таблици тут же по названию? 
    private static final Integer UNCONFIRMED_STATUS = 8;

    @Override
    public List<FriendsRequest> findAllFriendsRequest() {
        return select(UNIVERSE_SELECT);
    }

    @Override
    public List<FriendsRequest> findAllSendFriendsRequestForUser(Users user) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE id_user_requesting = " + user.getId();
        return select(sqlSelect);
    }

    @Override
    public List<FriendsRequest> findAllRecievedFriendsRequestForUser(Users user) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE id_user_confirming = " + user.getId();
        return select(sqlSelect);
    }

    @Override
    public List<FriendsRequest> findAllUnconfirmedFriendsRequestForUser(Users user) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE id_user_confirming = " + user.getId()
                + " AND request_status = " + UNCONFIRMED_STATUS;
        return select(sqlSelect);
    }

    @Override
    public FriendsRequest findFriendsRequestByID(Integer idFriendsRequest) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE id = " + idFriendsRequest;
        //TODO проверку
        return select(sqlSelect).get(0);
    }

    @Override
    public boolean hasUsersFriendsRequest(Users firstUserId, Users secondUserId) {

        String sqlSelect = UNIVERSE_SELECT + " WHERE (id_user_requesting = " + firstUserId.getId()
                + " AND id_user_confirming = " + secondUserId.getId()
                + ") OR (id_user_requesting= " + secondUserId.getId()
                + " AND id_user_confirming = " + firstUserId.getId()+")";

        return !select(sqlSelect).isEmpty();
    }

    @Override
    public void addFriendsRequest(FriendsRequest friendsRequest) {
    //TODO проверку что не друзья, и нет такого риквеста
        String updateQuery = String.format(UNIVERSE_INSERT,
                friendsRequest.getIdUserRequesting(),
                friendsRequest.getIdUserConfirming(), UNCONFIRMED_STATUS);
        update(updateQuery);

    }

    @Override
    public void deleteFriendsRequest(FriendsRequest friendRequest) {
        String deleteQuery = String.format(UNIVERSE_DELETE, friendRequest.getId());
        update(deleteQuery);
    }

    @Override
    public void changeFriendsRequestStatus(FriendsRequest friendRequset, RequestStatusDictionary requestStatus) {
        String updateQuery = String.format(UPDATE_FRIENDS_REQUEST_STATUS, requestStatus.getId(),
                friendRequset.getId());
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

    private List<FriendsRequest> select(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<FriendsRequest> friendsRequestList = new ArrayList<FriendsRequest>();

        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {
                FriendsRequest friendrequest = new FriendsRequest();

                friendrequest.setId(resultSet.getInt(ID_COLUMN));
                friendrequest.setIdUserRequesting(resultSet.getInt(ID_USER_REQUESTING_COLUMN));
                friendrequest.setIdUserConfirming(resultSet.getInt(ID_USER_CONFIRMING_COLUMN));
                friendrequest.setRequestStatus(resultSet.getInt(REQUEST_STATUS_COLUMN));

                friendsRequestList.add(friendrequest);
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

        return friendsRequestList;
    }
}
