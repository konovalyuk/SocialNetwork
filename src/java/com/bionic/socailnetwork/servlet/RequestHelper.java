package com.bionic.socailnetwork.servlet;

import com.bionic.socailnetwork.commands.AddFriendRequest;
import com.bionic.socailnetwork.commands.AddNewUser;
import com.bionic.socailnetwork.commands.BlockUser;
import com.bionic.socailnetwork.commands.ChangeProfile;
import com.bionic.socailnetwork.commands.CommitChangesProfile;
import com.bionic.socailnetwork.commands.ConfirmFriend;
import com.bionic.socailnetwork.commands.ShowRequests;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import com.bionic.socailnetwork.commands.ICommand;
import com.bionic.socailnetwork.commands.Login;
import com.bionic.socailnetwork.commands.NoCommand;
import com.bionic.socailnetwork.commands.FindAllUserFriends;
import com.bionic.socailnetwork.commands.Img;
import com.bionic.socailnetwork.commands.Logout;
import com.bionic.socailnetwork.commands.Search;
import com.bionic.socailnetwork.commands.SendMessage;
import com.bionic.socailnetwork.commands.SetEn;
import com.bionic.socailnetwork.commands.SetRu;
import com.bionic.socailnetwork.commands.ShowBlock;
import com.bionic.socailnetwork.commands.ShowProfile;
import com.bionic.socailnetwork.commands.ViewMessages;
import com.bionic.socailnetwork.commands.ViewUserProfile;
import com.bionic.socailnetwork.commands.WriteMessage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private RequestHelper() {
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("profile", new ShowProfile());
        commands.put("search", new Search());
        commands.put("friends", new FindAllUserFriends());
        commands.put("addrequest", new AddFriendRequest());
        commands.put("view", new ViewUserProfile());
        commands.put("requests", new ShowRequests());
        commands.put("confirm", new ConfirmFriend());
        commands.put("changeprofile", new ChangeProfile());
        commands.put("commitChanges", new CommitChangesProfile());
        commands.put("newUser", new AddNewUser());
        commands.put("register", new ICommand() {
            public String execute(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException {
                return "/jsp/addNewUser.jspx";
            }
        });
        commands.put("writeMessage", new WriteMessage());
        commands.put("sendMessages", new SendMessage());
        commands.put("viewMessages", new ViewMessages());
        commands.put("RU", new SetRu());
        commands.put("EN", new SetEn());
        commands.put("showblock", new ShowBlock());
        commands.put("block", new BlockUser());
        commands.put("img", new Img());


    }

    public ICommand getCommand(HttpServletRequest request) throws IOException, IllegalStateException, ServletException {
        String action = request.getParameter("command");
        
        ICommand command = commands.get(action);
        if(command==null){
            command=new NoCommand();
        } 
        return command;
      }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
