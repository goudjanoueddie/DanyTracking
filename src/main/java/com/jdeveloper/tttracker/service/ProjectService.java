/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.service;

import com.jdeveloper.tttracker.domain.Project;
import com.jdeveloper.tttracker.vo.Result;
import java.util.List;

/**
 *
 * @author goudjanou
 */
public interface ProjectService {
    
    public Result<Project> store(Integer idProject,Integer idCompany,String projectName,String actionUsername);
    public Result<Project> remove(Integer idProject,String Username);
    public Result<Project> find(Integer idProject,String Username);
    public Result<List<Project>> findAll(String actionUsername);
    
}
