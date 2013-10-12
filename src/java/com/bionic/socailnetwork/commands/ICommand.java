package com.bionic.socailnetwork.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Катерина
 */
public interface ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException;
}
