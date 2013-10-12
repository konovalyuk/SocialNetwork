/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socialnetwork.filter;

import com.bionic.socialnetwork.locale.Localisation;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author admin
 */
public class LocalisationFilter implements Filter {

    private FilterConfig filterConfig;

    public void init(final FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, javax.servlet.ServletException {
        String locale = request.getParameter("locale");
        if(locale==null || locale.isEmpty()){
            locale="en";
        }
        Localisation myLocale = new Localisation(new Locale(locale, locale));
        filterConfig.getServletContext().setAttribute("welcome_lable", myLocale.WELCOME);
        request.setAttribute("name_lable", myLocale.NAME);
        request.setAttribute("surname_lable", myLocale.SURNAME);
        request.setAttribute("city_lable", myLocale.CITY);
        request.setAttribute("role_lable", myLocale.ROLE);
        request.setAttribute("friends_lable", myLocale.FRIENDS);
        request.setAttribute("logout_lable", myLocale.LOGOUT);
        request.setAttribute("search_lable", myLocale.SEARCH);
        request.setAttribute("request_lable", myLocale.FRIEND_REQUESTS);
        request.setAttribute("change_profile_lable", myLocale.CHANGE_PROFILE);
        request.setAttribute("view_messages_lable", myLocale.VIEW_MESSAGES);

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
