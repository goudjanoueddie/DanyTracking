/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdeveloper.tttracker.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableWebMvc
@Configuration
@ComponentScan("com.jdeveloper.tttracker.web")
public class WebAppConfig extends WebMvcConfigurerAdapter{

    
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        
        registry.addInterceptor(new UserInSessionInterceptor()).addPathPatterns(new String[]{
                "/**"
        }).excludePathPatterns("/security/**");
    
    }
    
}
