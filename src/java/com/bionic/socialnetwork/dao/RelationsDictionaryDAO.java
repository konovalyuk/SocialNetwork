package com.bionic.socialnetwork.dao;

import com.bionic.socialnetwork.idao.IRelationsDictionaryDAO;
import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.RelationsDictionary;
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
public class RelationsDictionaryDAO implements IRelationsDictionaryDAO {
    //relations_dictionary, id, description 

    private static final int ID_COLUMN = 1;
    private static final int DESCRIPTION_COLUMN = 2;
    private static final String UNIVERSE_SELECT = "SELECT * FROM relations_dictionary ";
    private static final String UNIVERSE_INSERT =
            "INSERT INTO relations_dictionary (description) VALUES ('%s')";
    private static final String UNIVERSE_DELETE = "DELETE FROM relations_dictionary"
            + " WHERE id = %d";

    @Override
    public List<RelationsDictionary> findAll() {
       return select(UNIVERSE_SELECT);
    }

    @Override
    public RelationsDictionary findRelationById(Integer id) {
        String selectQuery = UNIVERSE_SELECT+" WHERE id = "+id;
        return select(selectQuery).get(0);
    }

    @Override
    public void addRelation(RelationsDictionary relation) {
        String insertQuery = String.format(UNIVERSE_INSERT, relation.getDescription());
        update(insertQuery);
    }

    @Override
    public void deleteRelation(RelationsDictionary relation) {
        String deleteQuery = String.format(UNIVERSE_DELETE, relation.getId());
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

    private List<RelationsDictionary> select(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<RelationsDictionary> relationsList = new ArrayList<RelationsDictionary>();

        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {
                RelationsDictionary relation = new RelationsDictionary();

                relation.setId(resultSet.getInt(ID_COLUMN));
                relation.setDescription(resultSet.getString(DESCRIPTION_COLUMN));

                relationsList.add(relation);
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

        return relationsList;
    }
}
