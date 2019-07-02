/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.service;

import com.jdeveloper.tttracker.dao.CompanyDao;
import com.jdeveloper.tttracker.domain.Company;
import com.jdeveloper.tttracker.domain.User;
import com.jdeveloper.tttracker.vo.Result;
import com.jdeveloper.tttracker.vo.ResultFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("companyService")
public class CompanyServiceImpl extends AbstractService implements CompanyService {
    
    @Autowired
    protected CompanyDao companyDao;
    
    public CompanyServiceImpl(){
            super();
    }

    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    @Override
    public Result<Company> store(Integer idCompany, String companyName, String actionUsername) {
        
      User actionUser=userDao.find(actionUsername);
      if(!actionUser.isAdmin()){
          return ResultFactory.getFailResult(USER_NOT_ADMIN);
      }
      
      Company company;
      if(idCompany ==null){
            company=new Company();
      }else{
      
            company=companyDao.find(idCompany);
            
            if(company ==null){
                return ResultFactory.getFailResult("Unable to find company instance with ID="+idCompany);
            }
      }
      
      company.setCompanyName(companyName);
      
      if(company.getId() == null){
          companyDao.persist(company);
      }else{
          company=companyDao.merge(company);
      }
      
      return ResultFactory.getSuccessResult(company);
        
    }

    
    
    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    @Override
    public Result<Company> find(Integer idCompany, String actionUsername) {
        
        if(isValidUser(actionUsername)){
            
            Company company=companyDao.find(idCompany);
            return ResultFactory.getSuccessResult(company);
        
        }else {
            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED)
    @Override
    public Result<Company> remove(Integer idCompany, String actionUsername) {
        User actionUser = userDao.find(actionUsername);
        
        if(!actionUser.isAdmin()){
            return ResultFactory.getFailResult(USER_NOT_ADMIN);
        }
        
        if(idCompany == null){
            return ResultFactory.getFailResult("Unable to remove Company [null idCompany]");
        }
        
        Company company = companyDao.find(idCompany);
        
        if(company ==null){
            return ResultFactory.getFailResult("Unable to load Company for removal with idCompany="+idCompany);
        }else{
                if(company.getProjects()==null || company.getProjects().isEmpty()){
                    
                    companyDao.remove(company);
                    
                    String msg="Company"+ company.getCompanyName()+"was deleted by"+actionUsername;
                    logger.info(msg);
                    
                    return ResultFactory.getSuccessResultMsg(msg);
                
                }else{
                    
                    return ResultFactory.getFailResult("Company has projects assigned and could not be deleted");
                    
                }
        
        }
    }

    @Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
    @Override
    public Result<List<Company>> findAll(String actionUsername) {
        
        if(isValidUser(actionUsername)){
        
            return ResultFactory.getSuccessResult(companyDao.findAll());
            
        }else{
            
            return ResultFactory.getFailResult(USER_INVALID);
        
        }
    }
    
}
