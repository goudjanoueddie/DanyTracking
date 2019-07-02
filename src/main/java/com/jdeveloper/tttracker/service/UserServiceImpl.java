/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.service;

import com.jdeveloper.tttracker.dao.TaskLogDao;
import com.jdeveloper.tttracker.dao.UserDao;
import com.jdeveloper.tttracker.domain.User;
import com.jdeveloper.tttracker.vo.Result;
import com.jdeveloper.tttracker.vo.ResultFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {
    
    
    /*@Autowired
    protected UserDao userDao;*/
    
    @Autowired
    protected TaskLogDao tasklogDao;
    
    
    public  UserServiceImpl(){
        super();
    }
    

    @Transactional(readOnly=true,propagation=Propagation.REQUIRED)
    @Override
    public Result<User> store(String username, String firstName, String lastName, String email, String password, Character adminRole, String actionUsername) {
        
        User actionUser = userDao.find(actionUsername);
        User taskUser = userDao.find(actionUsername);
        
        if(actionUser == null || taskUser== null){
        
            return ResultFactory.getFailResult(USER_INVALID);
        }
        
        //User user = userDao.find(username);
        User user;
        
        if(username == null){
            //return ResultFactory.getFailResult("Unable to store empty User ");
            
            user =new User();
        }else{
            
            //user=userDao.find(username);
            user=userDao.find(actionUsername);
            
            if(user == null){
                
                return ResultFactory.getFailResult("Unable to find user instance with  ID="+username);
            }
        
            
        }
        
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdminRole(adminRole);
        
        if(user.getId()==null){
        
            userDao.persist(user);
        }else{
        
            user=userDao.merge(user);
        }
        
        return ResultFactory.getSuccessResult(user);
    }//end store method

    
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    @Override
    public Result<User> remove(String username, String actionUsername) {
        
        User actionUser=userDao.find(actionUsername);
        
        if(actionUser == null){
            return ResultFactory.getFailResult(USER_INVALID);
        }
        
        if(!actionUser.isAdmin()){
            return ResultFactory.getFailResult(USER_NOT_ADMIN);
        }
        
        /*if(actionUser == null){
        
            return ResultFactory.getFailResult("Unable to load User for removal with idUser"+username);
        }*/else{
        
            userDao.remove(actionUser);
            String msg="User"+ actionUser.getLastName()+"was deleted by"+actionUsername;
            
            return ResultFactory.getSuccessResultMsg(msg);
            
        }
        
    }

    
    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    @Override
    public Result<User> find(String username, String actionUsername) {
        
        if(isValidUser(actionUsername)){
        
            User user =userDao.find(username);
            return ResultFactory.getSuccessResult(user);
        }else{
        
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    
    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    @Override
    public Result<List<User>> findAll(String actionUsername) {
        
        if(isValidUser(actionUsername)){
            return ResultFactory.getSuccessResult(userDao.findAll());
        }else{
        
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    /*@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    @Override
    public Result<User> findByUsernamePassword(String username, String password,String actionUsername) {
        
        //User user=userDao.findByUsernamePassword(username, password);
        User actionUser=userDao.find(actionUsername);
        
        if(actionUser == null){
        
            return ResultFactory.getFailResult(USER_INVALID);
        }
        
        
        if(actionUser.isAdmin()){
        
            return ResultFactory.getSuccessResult(userDao.findByUsernamePassword(username, password));
            
        }else{
            
            return ResultFactory.getFailResult("Unable to find this user. The people does not have the right permissin "+username +"pa");
        
        }
    }*/

    @Override
    public Result<User> findByUsernamePassword(String username, String password) {
        
        User actionUser=userDao.find(username);
        
        if(actionUser ==null){
            return ResultFactory.getFailResult(USER_INVALID);
        }
        
        if(isValidUser(username)){
        
            return ResultFactory.getSuccessResult(userDao.findByUsernamePassword(username, password));
        
        }else{
        
            return ResultFactory.getFailResult("Unable to find this user. The people does not have the right permission "+username +password);
        }
    }
    
}
