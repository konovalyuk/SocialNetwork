package logic;

import com.bionic.socialnetwork.dao.FriendsDAO;
import com.bionic.socialnetwork.dao.FriendsRequestDAO;
import com.bionic.socialnetwork.dao.MessagesDAO;
import com.bionic.socialnetwork.dao.RelationsDictionaryDAO;
import com.bionic.socialnetwork.dao.RequestStatusDictionaryDAO;
import com.bionic.socialnetwork.dao.UserDAO;
import com.bionic.socialnetwork.daofactory.DAOFactory;
import com.bionic.socailnetwork.connections.DBConnection;
import com.bionic.socailnetwork.entity.Friends;
import com.bionic.socailnetwork.entity.FriendsRequest;
import com.bionic.socailnetwork.entity.Messages;
import com.bionic.socailnetwork.entity.RelationsDictionary;
import com.bionic.socailnetwork.entity.RequestStatusDictionary;
import com.bionic.socailnetwork.entity.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Катерина
 */
public class Test {
    public static void main(String[] args) {
    /*try{
        Connection connection = new DBConnection().connect();
        Statement statement = connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery("Select * from users");
        while(resultSet.next())
            System.out.println(resultSet.getString("name"));
        
        resultSet.close();
        statement.close();
        connection.close();
        
    }catch(SQLException e){
        System.err.print("err in main"+e);
    }
    */
        UserDAO userDao = new UserDAO();
//        
//        for(Users user:userDao.findAllUsers()){
//            System.out.println("from test "+user.getName()+" "+user.getCity());
//        }
//        
//        for(Users user:userDao.findAllUsersByName("Katerina")){
//            System.out.println("from test "+user.getId()+" "+user.getCity());
//        }
//        
//        Users user = userDao.findUserByEmail("mail@gmail.com");
//        System.out.println("find by email "+user.getName()+" "+user.getCity());
//         
//        System.out.println(user);
        //Почему в рамках одного запуска данные не обновляются?? не срабатывает комит? 
   //   userDao.changeEnable(user, true);
//      //  userDao.updateUserInfo(user.getId(), "Katerina", null, null, null);
//        System.out.println(userDao.findUserByID(2));
//        
//        FriendsDAO frDao = new FriendsDAO();
//        Friends fr = frDao.findFriendshipByID(1);
//        System.out.println(fr);
//        System.out.println(frDao.isUsersFreinds(2, 3));
//        //System.out.println(frDao.generateID());
//        Users u1 = userDao.findUserByID(4);
//        Friends newFr = new Friends(2, u1.getId(),user.getId(),1);
//       // frDao.addFriend(newFr);
//        //   frDao.deleteFriendship(newFr);
//        for(Friends friends: frDao.findAllFriends()){
//            System.out.println(friends);
//        }
//        
//        //////////////////
//        System.out.println("find all");
//        for(RequestStatusDictionary req: new RequestStatusDictionaryDAO().findAll())
//            System.out.println(req);
//        
//        System.out.println("find by id");
//        System.out.println(new RequestStatusDictionaryDAO().findRequestStatusById(1));
//        
//        RequestStatusDictionaryDAO relDao = new RequestStatusDictionaryDAO();
//        RequestStatusDictionary rs = new RequestStatusDictionary(2, "unconfirmed");
////        
//        System.out.println("add");
//        relDao.addRequestStatus(rs);
//        for(RequestStatusDictionary req: new RequestStatusDictionaryDAO().findAll())
//            System.out.println(req);
//        
//        System.out.println("delete");
//        relDao.deleteRequestStatus(4);
//        for(RequestStatusDictionary req: new RequestStatusDictionaryDAO().findAll())
//            System.out.println(req);
//        
    // test message
//    MessagesDAO md = new MessagesDAO();
//    for(Messages mes:md.findAllMessagesForTwoUsers(new Users(2), new Users(3))){
//        System.out.println(mes);
//    }
//
//      FriendsDAO frd = new DAOFactory().getFriendsDAO();
//      Users currentUser = new Users(2);
//        System.out.println("***********");
//        
//      for(Users fr :frd.findAllFriendsForUser(currentUser))
//            System.out.println(fr);
//      
        //String login = "olga@mail.com";
//        FriendsRequestDAO friendRequestDao = new FriendsRequestDAO();
//    
//        Users currentUser = userDao.findUserByEmail(login);
//        List<Users> list = new ArrayList<Users>();
//        Users tempUser = new Users();
//        List<FriendsRequest> friendRequestList = friendRequestDao.findAllUnconfirmedFriendsRequestForUser(currentUser);
//        for(FriendsRequest friendsRequest: friendRequestList){
//            tempUser = userDao.findUserByID(friendsRequest.getIdUserRequesting());
//            list.add(tempUser);
//            System.out.println(tempUser);
//        }
//        System.out.println(friendRequestDao.hasUsersFriendsRequest(tempUser, currentUser));
        
        String regexp="^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
        //System.out.println(mail.matches(regexp));
        String page="";
        String login = "marry@mail.com";
        String name = "name";
        String surname = "surname";
        String city = "city";
        String password = "password";
        Users currentUser = new Users(1, name, surname, city, true, login, password);
        
        if(login!=null&&!login.isEmpty()&&login.matches(regexp)&&!userDao.isUserExist(login)){
            System.out.println("adding");
            userDao.addUser(currentUser);
            page="/jsp/profile.jspx";
        }else{
            page="/jsp/error.jsp";
        }
        
        String recieverLogin="mail@gmail.com";
        Users reciever = userDao.findUserByEmail(recieverLogin);
        Users sender = userDao.findUserByEmail("olga@mail.com");
        //Users currentUser = userDao.findUserByEmail(senderLogin);

        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date();
//       // date.getTime();
        
//       // String currentDate = dateFormat.format(date);
        Date today = new Date();
        System.out.printf("Date: %tD%n", today);
//         System.out.printf("Date in dd/mm/yy format %td/%tm/%ty %n", today,today,today );
//       
         // date as July 25, 2012, difference between %td and %te is that
 // %td use leading zero while %te doesn't
//         System.out.printf("Today is %tB %te, %tY %tD %n", today,today,today,today);
//        System.out.printf("");


//javarevisited.blogspot.com/2012/08/how-to-format-string-in-java-printf.html#ixzz2aN5pCrH1
        String textMessage = "test";

        Messages message = new Messages(1, textMessage, reciever.getId(), sender.getId());
        MessagesDAO messagesDao = new MessagesDAO();
        System.out.println("date "+message.getDate());
        messagesDao.addMessage(message);
        
        
    }
    
}
