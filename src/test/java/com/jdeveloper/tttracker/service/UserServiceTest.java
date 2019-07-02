
package com.jdeveloper.tttracker.service;

import com.jdeveloper.tttracker.dao.TaskLogDao;
import com.jdeveloper.tttracker.dao.UserDao;
import com.jdeveloper.tttracker.domain.TaskLog;
import com.jdeveloper.tttracker.domain.User;
import com.jdeveloper.tttracker.vo.Result;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest extends AbstractServiceForTesting{
    
    @Autowired
    protected UserService userService;
    
    @Autowired
    protected TaskLogDao taskLogDao;
    
    @Autowired
    protected UserDao userDao;
    
    private final String TEST_USERNAME="jsmith";
    
    
    @Test
    public void testAddNew() throws Exception{
    
    String ADMIN_USERNAME="bjones";
    
    logger.debug("\n STARTED testAddNew()\n");
    
    Result<User> ar=userService.store("username","Eddie","GOUDJANOU","goudjanoueddie@gmail.com","Dany2020ets",'N',ADMIN_USERNAME);
    
    //should succed
    logger.debug(ar.getMsg());
    assertTrue(ar.issuccess());
    
    /*ar=userService.store(this.TEST_USERNAME,"Eddie","GOUDJANOU","goudjanoueddie@gmail.com","Dany2020ets",'N',ADMIN_USERNAME);
    logger.debug(ar.getMsg());
    assertTrue("Cannot assign email that is currently assigned to other user",!ar.issuccess());
    
    ar=userService.store("user100", "David", "Francis", "user100@tttracker.com", "", 'Y', ADMIN_USERNAME);
    logger.debug(ar.getMsg());
    assertTrue("Cannot set empty password for user",!ar.issuccess());
    
    ar=userService.store("user101", "David", "Francis", "", "validPwd", 'Y', ADMIN_USERNAME);
    logger.debug(ar.getMsg());
    assertTrue("Cannot set empty email for user",!ar.issuccess());
    
    ar=userService.store(this.TEST_USERNAME,"Eddie","GOUDJANOU","enoutondji@yahoo.fr","Dany2020ets",'Y',ADMIN_USERNAME);
    logger.debug(ar.getMsg());
    assertTrue("Assigning new email touser is allowed",ar.issuccess());*/
    
    logger.debug("\n FINISHED testAddNew()\n");
    

    }
    
}
