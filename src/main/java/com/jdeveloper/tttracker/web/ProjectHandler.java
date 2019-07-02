/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.web;

import com.jdeveloper.tttracker.domain.Project;
import com.jdeveloper.tttracker.domain.User;
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
@RequestMapping("/project")
public class ProjectHandler extends AbstractHandler{
    
    @Autowired
    protected ProjectService projectService;
    
    @RequestMapping(value="/find",method=RequestMethod.GET,produces = {"application/json"})
    @ResponseBody
    public String find(@RequestParam(value="idProject",required=true)Integer idProject,HttpServletRequest request){
       
       User sessionUser=getSessionUser(request);
        
       /*if(sessionUser == null){
            return getJsonErrorMsg("User is not logged on");
        }*/
       
       Result<Project> ar =projectService.find(idProject, sessionUser.getUsername());
       
       if(ar.issuccess()){
           
            return getJsonSuccessData(ar.getData());
       }else{
       
            return getJsonErrorMsg(ar.getMsg());
       }
        
       
    }//end find method
    
    
    @RequestMapping(value ="/store",method=RequestMethod.POST,produces={"application/json"})
    @ResponseBody
    public String store(@RequestParam(value="data",required=true)String jsonData,HttpServletRequest request){
        
        User sessionUser = getSessionUser(request);
        /*if(sessionUser ==null){
            return getJsonErrorMsg("User is not logged on");
        }*/
        
        JsonObject jsonObj = parseJsonObject(jsonData);
        
        Result<Project> ar= projectService.store(getIntegerValue(jsonObj.get("idProject")), getIntegerValue(jsonObj.get("idCompany")), 
                            jsonObj.getString("projectName"), sessionUser.getUsername());
        
        if(ar.issuccess()){
            
            return getJsonSuccessData(ar.getData());
            
        }else{
            
            return getJsonErrorMsg(ar.getMsg());
            
        }
    
    }
    
    
    @RequestMapping(value="/remove",method=RequestMethod.POST,produces ={"application/json"})
    @ResponseBody
    public String remove(@RequestParam(value="data",required =true)String jsonData,HttpServletRequest request){
        
       
       User sessionUser=getSessionUser(request);
       /*if(sessionUser == null){
            return getJsonErrorMsg("User is not logged on");
        }*/
       
       JsonObject jsonObj=parseJsonObject(jsonData);
       
       Result<Project> ar = projectService.remove(getIntegerValue(jsonObj.get("idProject")), sessionUser.getUsername());
       if(ar.issuccess()){
           return getJsonSuccessMsg(ar.getMsg());
       }else{
           return getJsonErrorMsg(ar.getMsg());
       }
      
    }
    
    @RequestMapping(value="/findAll",method=RequestMethod.GET,produces={"application/json"})
    @ResponseBody
    public String findAll(HttpServletRequest request){
        
        
        
       User sessionUser=getSessionUser(request);
       /*if(sessionUser == null){
            return getJsonErrorMsg("User is not logged on");
        }*/
       
       Result<List<Project>> ar = projectService.findAll(sessionUser.getUsername());
       
            if (ar.issuccess()) {
            return getJsonSuccessData(ar.getData());
            } else {
            return getJsonErrorMsg(ar.getMsg());
            }
}
       
    
    }
    
    

