/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.dao;

import com.jdeveloper.tttracker.domain.Task;
import com.jdeveloper.tttracker.domain.TaskLog;
import com.jdeveloper.tttracker.domain.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author goudjanou
 */
public interface TaskLogDao extends GenericDao<TaskLog, Integer>{
    
    public List<TaskLog> findByUser(User user,Date startDate,Date endDate);
    public long findTaskLogCountByTask(Task task);
    public long findTaskLogCountByUser(User user);
    
}
