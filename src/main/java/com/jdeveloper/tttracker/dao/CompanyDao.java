/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.dao;

import com.jdeveloper.tttracker.domain.Company;
import java.util.List;

/**
 *
 * @author goudjanou
 */
public interface CompanyDao extends GenericDao<Company, Integer> {
    
    
    public List<Company> findAll();
    /*
    public Company find(Integer idCompany);
    public void persist(Company company);
    public Company merge(Company company);
    public void remove(Company company);
    */
}
