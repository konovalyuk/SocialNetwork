/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socialnetwork.locale;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author admin
 */
public class Localisation {

    final ResourceBundle locale;
    public final String WELCOME;
    public final String NAME;
    public final String SURNAME;
    public final String CITY;
    public final String ROLE;
    public final String FRIENDS;
    public final String FRIEND_REQUESTS;
    public final String CHANGE_PROFILE;
    public final String LOGIN;
    public final String LOGOUT;
    public final String VIEW_MESSAGES;
    public final String SEARCH;

    public Localisation(Locale currentLocale) {
        locale = ResourceBundle.getBundle("com.bionic.socialnetwork.locale.localisation", currentLocale);
        WELCOME = locale.getString("welcome");
        NAME = locale.getString("name");
        SURNAME = locale.getString("surname");
        CITY = locale.getString("welcome");
        ROLE = locale.getString("role");
        FRIENDS = locale.getString("friends");
        FRIEND_REQUESTS = locale.getString("friend_requests");
        CHANGE_PROFILE = locale.getString("change_profile");
        LOGIN = locale.getString("login");
        LOGOUT = locale.getString("logout");
        VIEW_MESSAGES = locale.getString("view_messages");
        SEARCH = locale.getString("search");
    }
}
