/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socialnetwork.dao;

import com.bionic.socialnetwork.idao.IRequestStatusDictionaryDAO;
import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.RequestStatusDictionary;
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
public class RequestStatusDictionaryDAO implements IRequestStatusDictionaryDAO {
    //requests_dictionary, id, description 

    private static final int ID_COLUMN = 1;
    private static final int DESCRIPTION_COLUMN = 2;
    private static final String UNIVERSE_SELECT = "SELECT * FROM request_status_dictionary";
    private static final String UNIVERSE_INSERT =
            "INSERT INTO request_status_dictionary (description) VALUES ('%s')";
    private static final String UNIVERSE_DELETE = "DELETE FROM request_status_dictionary"
            + " WHERE id = %d";

    @Override
    public List<RequestStatusDictionary> findAll() {
        return select(UNIVERSE_SELECT);
    }

    @Override
    public RequestStatusDictionary findRequestStatusById(Integer id) {
        String selectQuery = UNIVERSE_SELECT + " WHERE id = " + id;
        return select(selectQuery).get(0);
    }

    @Override
    public RequestStatusDictionary findRequestStatusByDescription(String description) {
        String selectQuery = UNIVERSE_SELECT + " WHERE description = '" + description+"'";
        return select(selectQuery).get(0);
    }

    @Override
    public void addRequestStatus(RequestStatusDictionary request) {
        String insertQuery = String.format(UNIVERSE_INSERT, request.getDescription());
        update(insertQuery);
    }

    @Override
    public void deleteRequestStatus(RequestStatusDictionary request) {
        String deleteQuery = String.format(UNIVERSE_DELETE, request.getId());
        update(deleteQuery);
    }

    @Override
    public void deleteRequestStatus(Integer idRequest) {
        String deleteQuery = String.format(UNIVERSE_DELETE, idRequest);
        update(deleteQuery);
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

    private List<RequestStatusDictionary> select(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<RequestStatusDictionary> requestsList = new ArrayList<RequestStatusDictionary>();

        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {
                RequestStatusDictionary request = new RequestStatusDictionary();

                request.setId(resultSet.getInt(ID_COLUMN));
                request.setDescription(resultSet.getString(DESCRIPTION_COLUMN));

                requestsList.add(request);
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

        return requestsList;
    }
}
