/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.web;

import com.jdeveloper.tttracker.domain.Company;
import com.jdeveloper.tttracker.domain.User;
import com.jdeveloper.tttracker.service.CompanyService;
import com.jdeveloper.tttracker.service.ProjectService;
import com.jdeveloper.tttracker.vo.Result;
import static com.jdeveloper.tttracker.web.SecurityHelper.getSessionUser;
import java.util.List;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/company")
public class CompanyHandler extends AbstractHandler {
    
    
    @Autowired
    protected CompanyService companyService;
    
    @Autowired
    protected ProjectService projectService;
    
    
    @RequestMapping(value="/find",method=RequestMethod.GET,produces={"application/json"})
    @ResponseBody
    public String find(@RequestParam(value="idCompany",required=true) Integer idCompany,HttpServletRequest request){
        
        User sessionUser=getSessionUser(request);
        /*if(sessionUser == null){
            return getJsonErrorMsg("User is not logged on");
        }*/
        
        Result<Company> ar=companyService.find(idCompany, sessionUser.getUsername());
        
        if(ar.issuccess()){
            
            return getJsonSuccessData(ar.getData());
            
        }else{
            
            return getJsonErrorMsg(ar.getMsg());
            
        }
    
    }//end find method
    
    @RequestMapping(value="/store",method=RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String store(@RequestParam(value="data",required=true)String jsonData,HttpServletRequest request){
        
        User sessionUser=getSessionUser(request);
        /*if(sessionUser ==null){
            return getJsonErrorMsg("User is not logged on");
        }*/
        
        JsonObject jsonObj=parseJsonObject(jsonData);
        
        Result<Company> ar=companyService.store(getIntegerValue(jsonObj.get("idCompany")),jsonObj.getString("companyName"),sessionUser.getUsername());
        
        if(ar.issuccess()){
            return getJsonSuccessData(ar.getData());
        }else{
        
            return getJsonErrorMsg(ar.getMsg());
        }
    
    }
    
    
    @RequestMapping(value="/findAll",method=RequestMethod.GET,produces={"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){
        
        User sessionUser = getSessionUser(request);
        /*if(sessionUser ==null){
        
            return getJsonErrorMsg("User is not logged on");
        }*/
        
        Result<List<Company>> ar = companyService.findAll(sessionUser.getUsername());
        if(ar.issuccess()){
        
            return getJsonSuccessData(ar.getData());
            
        }else{
            
            return getJsonErrorMsg(ar.getMsg());
            
        }
    
    }
    
    
    
    @RequestMapping(value="/remove",method=RequestMethod.POST,produces={"application/json"})
    @ResponseBody
    public String remove(@RequestParam(value="data",required=true) String jsonData,HttpServletRequest request){
        
        User sessionUser = getSessionUser(request);
        /*if(sessionUser ==null){
        
            return getJsonErrorMsg("User is no logged on");
        }*/
        
        JsonObject jsonObj = parseJsonObject(jsonData);
        Result<Company> ar = companyService.remove(getIntegerValue(jsonObj.get("idCompany")),sessionUser.getUsername());
        
        if(ar.issuccess()){
            
            return getJsonSuccessMsg(ar.getMsg());
        }else{
        
            return getJsonErrorMsg(ar.getMsg());
        }
    
    }
    
    
    
    
    
}
