package com.bionic.socialnetwork.dao;

import com.bionic.socialnetwork.idao.IUserDAO;
import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.RoleDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Катерина
 */
public class UserDAO implements IUserDAO {

    private static final int ID_COLUMN = 1;
    private static final int NAME_COLUMN = 2;
    private static final int SURNAME_COLUMN = 3;
    private static final int CITY_COLUMN = 4;
    private static final int ENABLE_COLUMN = 5;
    private static final int EMAIL_COLUMN = 6;
    private static final int PASSWORD_COLUMN = 7;
    private static final int PHOTO_COLUMN = 8;
    private static final int ROLE_COLUMN = 9;
    
    private static final String UNIVERSE_SELECT = "SELECT * FROM users ";
    private static final String UNIVERSE_UPDATE_USER_INFO =
            "UPDATE users SET name = '%s', surname = '%s', city = '%s', password = '%s'"
            + " WHERE id = %d";
    private static final String UPDATE_USERS_SET_ENABLE = "UPDATE users SET enable = %b WHERE id = %d";
    private static final String UPDATE_USERS_SET_ROLE = "UPDATE users SET role = %d WHERE id = %d";
    private static final String UNIVERSE_INSERT = 
            "INSERT INTO users (name, surname, city, enable,`e-mail`,password,role) "
            + "VALUES ('%s', '%s', '%s',%b, '%s', '%s','%d')";
    
    private static final String UNIVERSE_DELETE = "DELETE FROM users WHERE id = %d";
    private static final int ADMIN_ROLE = 2;
    private static final int USER_ROLE = 1;
    
    public List<Users> findAllUsers() {
        return select(UNIVERSE_SELECT);
    }

    public List<Users> findAllUsersByName(String name) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE name = '" + name + "'";
        return select(sqlSelect);
    }

    public List<Users> findAllUsersBySurname(String surname) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE surname = '" + surname + "'";
        return select(sqlSelect);
    }

    public List<Users> findAllUsersByNameAndSurname(String name, String surname) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE name = '" + name + "' and surname ='" + surname + "'";
        return select(sqlSelect);
    }

    public List<Users> findAllUsersByCity(String city) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE city = '" + city + "'";
        return select(sqlSelect);
    }
    public List<Users> findAllUsersByAnyParameter(String name, String surname,String city){
        String sqlSelect = UNIVERSE_SELECT;
        if(city==null || city.isEmpty())
            sqlSelect+=" WHERE city is not null";
        else
            sqlSelect+=" WHERE city = '"+city+"'";
        
        if(name==null || name.isEmpty())
            sqlSelect+=" and name is not null";
        else
            sqlSelect+=" and name = '"+name+"'";
        
        if(surname==null || surname.isEmpty())
            sqlSelect+=" and surname is not null";
        else
            sqlSelect+=" and surname= '"+surname+"'";
        
        return select(sqlSelect);
    }
   
    public Users findUserByID(Integer id) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE id = " + id;
        Users user = select(sqlSelect).get(0);
        return user;
    }

    public Users findUserByEmail(String email) {
        String sqlSelect = UNIVERSE_SELECT + " WHERE `e-mail` = '" + email + "'";
        List<Users> listUser = select(sqlSelect);
        Users user=new Users();
        
        if(listUser.size()>0)
        {
            user = listUser.get(0);
        }
        return user;
    }

    public void addUser(Users user) {
      String name = user.getName();
      String surname = user.getSurname();
      String city = user.getCity();
      boolean enable = user.getEnable();
      String eMail = user.getEMail();
      String password = user.getPassword();
//      int role = user.getRole().getId();
      String insertQuery = String.format(UNIVERSE_INSERT,
              name, surname, city, enable,eMail,password, USER_ROLE);
      System.out.println("from insert "+insertQuery);
      
      update(insertQuery);
    }

    public void deleteUser(Users user) {
        update(String.format(UNIVERSE_DELETE,user.getId()));
    }

    public void deleteUser(Integer idUser) {
        update(String.format(UNIVERSE_DELETE,idUser));
    }

    //нужно ли делать отдельные апдейты только для имени, только для парол и тд?)
    public void updateUserInfo(Integer id, String name,
            String surname, String city, String password) {

        Users updatingUser = findUserByID(id);

        if (name==null||name.isEmpty()) {
            name = updatingUser.getName();
        }
        if (surname==null||surname.isEmpty()) {
            surname = updatingUser.getSurname();
        }
        if (city==null||city.isEmpty()) {
            city = updatingUser.getCity();
        }
        if (password==null||password.isEmpty()) {
            password = updatingUser.getPassword();
        }
        String updatingQuery = String.format(UNIVERSE_UPDATE_USER_INFO, name,surname,city,password,id);
        update(updatingQuery);
    }
    
    public void updateImg(Integer id,InputStream img){
        //Users updatingUser = findUserByID(id); 
        
        String updatePhoto = "UPDATE users SET photo = ? WHERE id = ?";
        Connection connection = null;
        
        PreparedStatement statement = null;
        try {
            
            connection = new DBConnection().connect();
            statement = connection.prepareStatement(updatePhoto);
            statement.setBlob(1, img);
            statement.setInt(2, id);
            statement.execute();
            
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
    public boolean isUserAdmin(Users user) {
        return user.getRole().equals(ADMIN_ROLE);
    }
    
    public boolean isUserExist(String email){
        Users currentUser = findUserByEmail(email);
                
        return currentUser.getId()!=-1;
    }

    public void changeEnable(Users user, boolean enable) {
        String updateQuery = String.format(UPDATE_USERS_SET_ENABLE,enable,user.getId());
        update(updateQuery);
    }

    public void changeRole(Users user, RoleDictionary role) {
        String updateQuery = String.format(UPDATE_USERS_SET_ROLE,role.getId(),user.getId());
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

    private List<Users> select(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Users> userList = new ArrayList<Users>();

        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getInt(ID_COLUMN));
                user.setName(resultSet.getString(NAME_COLUMN));
                user.setSurname(resultSet.getString(SURNAME_COLUMN));

                user.setCity(resultSet.getString(CITY_COLUMN));
                user.setEnable(resultSet.getBoolean(ENABLE_COLUMN));
                user.setEMail(resultSet.getString(EMAIL_COLUMN));

                user.setPassword(resultSet.getString(PASSWORD_COLUMN));
                user.setPhoto(resultSet.getBytes(PHOTO_COLUMN));
                user.setRole(resultSet.getInt(ROLE_COLUMN));

                userList.add(user);
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

        return userList;
    }
}
