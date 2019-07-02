/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.web;


import com.jdeveloper.tttracker.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityHelper {

    static final String SESSION_ATTRIB_USER="sessionuser";
    
    public static User getSessionUser(HttpServletRequest request){
        
        User user=null;
        HttpSession session = request.getSession(true);
        Object obj=session.getAttribute(SESSION_ATTRIB_USER);
        
        if(obj !=null && obj instanceof User){
            user=(User)obj;
        }
        
        return user;
        
    }
}
