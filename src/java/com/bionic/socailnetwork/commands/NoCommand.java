package com.bionic.socailnetwork.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "no such command yet");
        String page = "/jsp/error.jsp";
        return page;
    }
}
