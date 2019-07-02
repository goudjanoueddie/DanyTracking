/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.dao;

import com.jdeveloper.tttracker.domain.Project;
import java.util.List;

/**
 *
 * @author goudjanou
 */
public interface ProjectDao extends GenericDao<Project,Integer>{
    
    
    public List<Project> findAll();
    
    /*public Project find(Integer idProject);
    public void persist(Project project);
    public Project merge(Project project);
    public void remove(Project project);*/
    
}
