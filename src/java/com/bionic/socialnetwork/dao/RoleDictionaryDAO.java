package com.bionic.socialnetwork.dao;

import com.bionic.socialnetwork.idao.IRoleDictionaryDAO;
import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.RoleDictionary;
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
public class RoleDictionaryDAO implements IRoleDictionaryDAO{
  
    private static final int ID_COLUMN = 1;
    private static final int DESCRIPTION_COLUMN = 2;
    private static final String UNIVERSE_SELECT = "SELECT * FROM role_dictionary";
    private static final String UNIVERSE_INSERT =
            "INSERT INTO role_dictionary (description) VALUES ('%s')";
    private static final String UNIVERSE_DELETE = "DELETE FROM role_dictionary"
            + " WHERE id = %d";

    @Override
    public List<RoleDictionary> findAll() {
       return select(UNIVERSE_SELECT);
    }

    @Override
    public RoleDictionary findRoleById(Integer id) {
        String selectQuery = UNIVERSE_SELECT+" WHERE id = "+id;
        return select(selectQuery).get(0);
    }

    @Override
    public void addRole(RoleDictionary role) {
        String insertQuery = String.format(UNIVERSE_INSERT, role.getDescription());
        update(insertQuery);
    }

    @Override
    public void deleteRole(RoleDictionary role) {
        String deleteQuery = String.format(UNIVERSE_DELETE, role.getId());
        update(deleteQuery);
    }

    @Override
    public void deleteRole(Integer idRole) {
        String deleteQuery = String.format(UNIVERSE_DELETE, idRole);
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

    private List<RoleDictionary> select(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<RoleDictionary> rolesList = new ArrayList<RoleDictionary>();

        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {
                RoleDictionary role = new RoleDictionary();

                role.setId(resultSet.getInt(ID_COLUMN));
                role.setDescription(resultSet.getString(DESCRIPTION_COLUMN));

                rolesList.add(role);
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
      return rolesList;
    }
}
