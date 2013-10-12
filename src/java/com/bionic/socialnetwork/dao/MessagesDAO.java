package com.bionic.socialnetwork.dao;

import com.bionic.socialnetwork.idao.IMessagesDAO;
import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.MessageStatusDictionary;
import com.bionic.socailnetwork.entity.Messages;
import com.bionic.socailnetwork.entity.Users;
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
public class MessagesDAO implements IMessagesDAO {
//CREATE TABLE messages 
//ID , ID_USER_SENDER , ID_USER_RECIEVER DATE,TIME MESSAGE_STATUS TEXT_MESSAGE 

    private static final int ID_COLUMN = 1;
    private static final int ID_USER_SENDER_COLUMN = 2;
    private static final int ID_USER_RECIEVER_COLUMN = 3;
    private static final int DATE_COLUMN = 4;
    private static final int TIME_COLUMN = 5;
    private static final int MESSAGE_STATUS_COLUMN = 6;
    private static final int TEXT_MESSAGE_COLUMN = 7;
    private static final String UNIVERSE_SELECT = "SELECT * FROM messages";
    private static final String UPDATE_MESSAGE_STATUS = "UPDATE messages "
            + "SET message_status = %d WHERE id = %d";
    private static final String UNIVERSE_INSERT =
            "INSERT INTO messages (id_user_sender, id_user_reciever, date,time, "
            + "message_status, text_message) VALUES (%d, %d, `%s`,`%s`, %d, '%s')";
    private static final String UNIVERSE_DELETE = "DELETE FROM messages WHERE id = %d";

    @Override
    public List<Messages> findAllSendMessagesForUser(Users user) {
        int id = user.getId();
        String selectQuery = UNIVERSE_SELECT + " WHERE id_user_sender = " + id;
        return select(selectQuery);
    }

    @Override
    public List<Messages> findAllRecievedMessagesForUser(Users user) {
        int id = user.getId();
        String selectQuery = UNIVERSE_SELECT + " WHERE id_user_reciever = " + id;
        return select(selectQuery);
    }

    @Override
    public Messages findMessageById(Integer id) {
        String selectQuery = UNIVERSE_SELECT + " WHERE id_user_reciever = " + id;
        return select(selectQuery).get(0);
    }

    @Override
    public List<Messages> findAllMessagesForTwoUsers(Users sender, Users reciever) {
        int idSender = sender.getId();
        int idReciever = reciever.getId();

        String selectQuery = UNIVERSE_SELECT + " WHERE id_user_reciever = " + idReciever
                + " AND id_user_sender = " + idSender;
        System.out.println(selectQuery);
        return select(selectQuery);
    }

    @Override
    public void addMessage(Messages message) {
        //ID , ID_USER_SENDER , ID_USER_RECIEVER DATE,TIME MESSAGE_STATUS TEXT_MESSAGE 
//        
//        String insertQuery = String.format(UNIVERSE_INSERT,
//                message.getIdUserSender(),
//                message.getIdUserReciever(),
//                message.getDate().toString(),
//                message.getTime().toString(),
//                message.getMessageStatus(),
//                message.getTextMessage());
//        System.out.println("from add message "+insertQuery);
//        update(insertQuery);
        final String INSERT_STATEMENT =
            "INSERT INTO messages (id_user_sender, id_user_reciever, date,time, "
            + "message_status, text_message) VALUES (?,?,?,?,?,?)";
    
        Connection connection = null;
        PreparedStatement statement = null;
        try {

            connection = new DBConnection().connect();
            statement = connection.prepareStatement(INSERT_STATEMENT);
            statement.setInt(1,message.getIdUserSender());
            statement.setInt(2,message.getIdUserReciever());
            statement.setDate(3, message.getDate());
            statement.setTime(4, message.getTime());
            statement.setInt(5,message.getMessageStatus());
            statement.setString(6, message.getTextMessage());
            statement.executeUpdate();

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

    @Override
    public void deleteMessage(Messages message) {
        String deleteQuery = String.format(UNIVERSE_DELETE, message.getId());
        update(deleteQuery);
    }

    @Override
    public void changeMessageStatuse(Messages message, MessageStatusDictionary status) {
        String updateQuery = String.format(UPDATE_MESSAGE_STATUS, status.getId(), message.getId());
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

    private List<Messages> select(String sqlSelect) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Messages> messageList = new ArrayList<Messages>();

        try {
            connection = new DBConnection().connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSelect);

            while (resultSet.next()) {
                Messages message = new Messages();
                message.setId(resultSet.getInt(ID_COLUMN));
                message.setDate(resultSet.getDate(DATE_COLUMN));
                message.setTime(resultSet.getTime(TIME_COLUMN));
                message.setIdUserReciever(resultSet.getInt(ID_USER_RECIEVER_COLUMN));
                message.setIdUserSender(resultSet.getInt(ID_USER_SENDER_COLUMN));
                message.setMessageStatus(resultSet.getInt(MESSAGE_STATUS_COLUMN));
                message.setTextMessage(resultSet.getString(TEXT_MESSAGE_COLUMN));

                messageList.add(message);
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

        return messageList;
    }
}
