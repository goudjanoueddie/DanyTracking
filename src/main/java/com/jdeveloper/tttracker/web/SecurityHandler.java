/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.web;

import com.jdeveloper.tttracker.domain.User;
import com.jdeveloper.tttracker.service.UserService;
import com.jdeveloper.tttracker.vo.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.jdeveloper.tttracker.web.SecurityHelper.SESSION_ATTRIB_USER;

/**
 *
 * @author goudjanou
 */

@Controller
@RequestMapping("/security")
public class SecurityHandler extends AbstractHandler{
    
    @Autowired
    protected UserService userService;
    
    
    @RequestMapping(value = "/logon",method = RequestMethod.POST,produces ={"application/json"})
    @ResponseBody
    public String logon(
                @RequestParam(value="username",required=true)String username,
                @RequestParam(value="password",required=true)String password,HttpServletRequest request
            ){
        
        Result<User> ar = userService.findByUsernamePassword(username, password);
        
            if(ar.issuccess()){
                User user=ar.getData();
                HttpSession session=request.getSession(true);
                session.setAttribute(SESSION_ATTRIB_USER, user);
                return getJsonSuccessData(user);
            }else{
                return getJsonErrorMsg(ar.getMsg());
            
            }
    
    }//end logon method
    
    
    @RequestMapping(value="/logout",produces={"application/json"})
    public String logout(HttpServletRequest request){
        
        HttpSession session=request.getSession(true);
        session.removeAttribute(SESSION_ATTRIB_USER);
        return getJsonSuccessMsg("User  logged out...");
        
    }
    
}
