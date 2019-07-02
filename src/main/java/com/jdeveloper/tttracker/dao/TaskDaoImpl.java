/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.dao;

import com.jdeveloper.tttracker.domain.Task;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("taskDao")
@Transactional
public class TaskDaoImpl extends GenericDaoImpl<Task,Integer> implements TaskDao{
    
    public TaskDaoImpl(){
        super(Task.class);
    }

    @Override
    public List<Task> findAll() {
        
        return em.createNamedQuery("Task.findAll").getResultList();
    }
    
}
