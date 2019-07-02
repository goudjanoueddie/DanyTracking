/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.dao;

import com.jdeveloper.tttracker.domain.Task;
import java.util.List;

/**
 *
 * @author goudjanou
 */
public interface TaskDao extends GenericDao<Task,Integer> {
    
    
    public List<Task> findAll();
    /*public Task find(Integer idtask);
    public void persist(Task task);
    public Task merge(Task task);
    public void remove(Task task);*/
}
