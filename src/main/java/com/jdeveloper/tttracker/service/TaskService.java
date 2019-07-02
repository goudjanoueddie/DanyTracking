/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.service;

import com.jdeveloper.tttracker.domain.Task;
import com.jdeveloper.tttracker.vo.Result;
import java.util.List;

/**
 *
 * @author goudjanou
 */
public interface TaskService {
    
    public Result<Task> store(Integer idTask,Integer idProject,String taskName,String actionUsername);
    public Result<Task> remove(Integer idTask,String actionUsername);
    public Result<Task> find(Integer idTask,String Username);
    public Result<List<Task>> findAll(String actionUsername);
    
}
