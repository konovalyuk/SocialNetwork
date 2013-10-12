package com.bionic.socialnetwork.idao;

import com.bionic.socailnetwork.entity.RoleDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Катерина
 */
public interface IUserDAO {
    List<Users> findAllUsers();
    List<Users> findAllUsersByName(String name);
    List<Users> findAllUsersBySurname(String surname);
    List<Users> findAllUsersByNameAndSurname(String name, String surname);
    List<Users> findAllUsersByCity(String city);
    List<Users> findAllUsersByAnyParameter(String name, String surname,String city);
    
    Users findUserByID(Integer id);
    Users findUserByEmail(String email);
    
    void addUser(Users user);
    
    void deleteUser(Users user);
    
    //нужно ли делать отдельные апдейты только для имени, только для парол и тд?)
    void updateUserInfo(Integer id, String name, String surname, String city, String password);
    void updateImg(Integer id,InputStream img);
    boolean isUserAdmin(Users user);
    boolean isUserExist(String email);
    void changeEnable(Users user, boolean enable); 
    void changeRole(Users user, RoleDictionary role);
}
