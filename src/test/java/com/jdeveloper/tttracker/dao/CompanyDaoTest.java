/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.dao;

import com.jdeveloper.tttracker.domain.Company;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



public class CompanyDaoTest extends AbstractDaoForTesting{
    
    
    public CompanyDaoTest(){
    }
    
    
    @Test
    public void testFind() throws Exception{
        
        logger.debug("\n STARTED testFind() \n");
        List<Company> allItems=companyDao.findAll();
        
        assertTrue(allItems.size()>0);
        
        Company c1=allItems.get(0);
        int id=c1.getId();
        
        Company c2=companyDao.find(id);
        assertTrue(c1.equals(c2));
        
        logger.debug("\n FINISHED testFind() \n");
        
           
    }
    

    @Test
    public void testFindAll() throws Exception{
    
    logger.debug("\n STARTED testFindAll() \n");
    int rowCount= countRowsInTable("ttt_company");
    
    if(rowCount >0){
    
        List<Company> allItems=companyDao.findAll();
        assertTrue("Company.findAll list not equal to r of table ttt_company",rowCount == allItems.size());
        
    }else{
    
        throw new IllegalStateException("INVALID TESTING SCENARIO:Company table is empty");
    }
    
    logger.debug("\n FINISHED testFindAll() \n");

}
    
    @Test
    public void testPersist() throws Exception{
        
        logger.debug("\n STARTED testPersist() \n");
        Company c =new Company();
        final String NEW_NAME="PERSIST test Company name";
        c.setCompanyName(NEW_NAME);
        
        companyDao.persist(c);
        
        assertTrue(c.getId()!=null);
        assertTrue(c.getCompanyName().equals(NEW_NAME));
        
        logger.debug("\n FINISHED testPersist() \n");
    
    }
    
    @Test
    public void testMerge() throws Exception{
    
        logger.debug("\n STARTED testMerge() \n");
        final String NEW_NAME="MERGE Test Company New Name";
        Company c=companyDao.findAll().get(0);
        c.setCompanyName(NEW_NAME);
        
        c=companyDao.merge(c);
        assertTrue(c.getCompanyName().equals(NEW_NAME));
        
        logger.debug("\n FINISHED testMerge() \n");
    }
    
    @Test
    public void testRemove()throws Exception{
        
        logger.debug("\n STARTED testRemove() \n");
        Company c= companyDao.findAll().get(0);
        
        companyDao.remove(c);
        List<Company> allItems=companyDao.findAll();
        
        assertTrue("Deleted company may not be in findAll List",!allItems.contains(c) );
        logger.debug("\nFINISHED testRemove()\n");
    
    }
    
    
    
}
