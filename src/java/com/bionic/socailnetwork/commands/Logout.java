/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Катерина
 */
public class Logout implements ICommand{
        public String execute(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException,IOException{
             HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("auth",false);
            session.invalidate();
        }
            return "/index.jsp";
        }

    
}
