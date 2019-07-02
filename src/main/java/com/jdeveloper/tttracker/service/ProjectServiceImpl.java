/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.service;

import com.jdeveloper.tttracker.dao.ProjectDao;
import com.jdeveloper.tttracker.domain.Project;
import com.jdeveloper.tttracker.domain.User;
import com.jdeveloper.tttracker.vo.Result;
import com.jdeveloper.tttracker.vo.ResultFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author goudjanou
 */

@Transactional
@Service("projectService")
public class ProjectServiceImpl extends AbstractService implements ProjectService{
    
    @Autowired
    protected ProjectDao projectDao;
    
    
    public ProjectServiceImpl(){
        super();
    }

    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    @Override
    public Result<Project> store(Integer idProject, Integer idCompany, String projectName, String actionUsername) {
        
        User actionUser = userDao.find(actionUsername);
        if(!actionUser.isAdmin()){
        
            return ResultFactory.getFailResult(USER_NOT_ADMIN);
        }
        
        Project project;
        if(idProject == null){
            project=new Project();
        }else{
        
            project = projectDao.find(idProject);
            
            if(project == null){
            
                return ResultFactory.getFailResult("Unable to find project instance with ID="+idProject);
            }
        }
        
        project.setProjectName(projectName);
        
        
        if(project.getIdProject() == null){
        
            projectDao.persist(project);
        }else{
        
            project=projectDao.merge(project);
        
        }
        
        return ResultFactory.getSuccessResult(project);
    }//end store method

    
    @Transactional(readOnly =false,propagation=Propagation.REQUIRED)
    @Override
    public Result<Project> remove(Integer idProject, String Username) {
        User actionUser = userDao.find(Username);
        
        if(!actionUser.isAdmin()){
            
            return ResultFactory.getFailResult(USER_NOT_ADMIN);
            
        }
        
        if(idProject == null){
        
            return ResultFactory.getFailResult("unable to remove Project[null idProject]");
        }else{
        
            Project project = projectDao.find(idProject);
            
            if(project == null){
            
                return ResultFactory.getFailResult("Unable to load Project for removal with idProject"+idProject);
            }else {
            
            projectDao.remove(project);
            String msg="Project"+project.getProjectName()+"was deleted by"+Username;
            logger.info(msg);
            return ResultFactory.getSuccessResultMsg(msg);
            }
            
            
            
        }
        
        
    }

    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    @Override
    public Result<Project> find(Integer idProject, String actionUsername) {
        
        if(isValidUser(actionUsername)){
        
            return ResultFactory.getSuccessResult(projectDao.find(idProject));
        }else{
            
            return ResultFactory.getFailResult(USER_INVALID);
        
        }
    }

    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    @Override
    public Result<List<Project>> findAll(String actionUsername) {
        
        if(isValidUser(actionUsername)){
            return ResultFactory.getSuccessResult(projectDao.findAll());
        }else{
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
}
