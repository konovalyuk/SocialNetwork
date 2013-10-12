package com.bionic.socailnetwork.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author Катерина
 */
public class DBConnection {

//    String JDBC_URL;
//    String LOGIN;
//    String PASSWORD;
//    final ResourceBundle dbconfig;

    public DBConnection() {
//        dbconfig = ResourceBundle.getBundle("connections.dbconnection");
//        JDBC_URL = dbconfig.getString("db.url");
//        LOGIN = dbconfig.getString("db.login");
//        PASSWORD = dbconfig.getString("db.password");
    }

    public Connection connect() {
        Context initCtx;
        Connection connection = null;
        
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/social_network_bionic");
            connection = ds.getConnection();
 //       connection = DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
           Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
         
        } catch (NamingException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;

    }
}
