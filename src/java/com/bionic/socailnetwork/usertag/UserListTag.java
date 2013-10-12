package com.bionic.socailnetwork.usertag;
import javax.servlet.jsp.tagext.TagSupport; import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter; import java.io.IOException; 
import java.util.Enumeration;

public class UserListTag extends TagSupport {

    public int doStartTag() throws JspException { //получение информации, передаваемой на страницу
        String currentUserRole = (String)pageContext.getSession().getAttribute("role");
        
        String str = "<form method=\"post\" >"
                + "<input type=\"hidden\" name=\"userlogin\" value=\"${user.EMail}\"/>" 
                + "<input type=\"hidden\" name=\"command\" value=\"showblock\" />"
                + "<input type=\"submit\" value=\"block user\" />"
                +"</form>" ;
                
        if(currentUserRole.equals("admin")){
        try {
            JspWriter out = pageContext.getOut();
            out.write(str);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        }
        return SKIP_BODY;
    }
}