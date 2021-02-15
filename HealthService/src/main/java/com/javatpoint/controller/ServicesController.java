package com.javatpoint.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ServicesController 
{
	WebDao userDao = new WebDao();  
@RequestMapping("/")
public String hello() 
{
return "Hello World";
}


@RequestMapping("/services") 
public List<Services> getUsers(){ 
   return userDao.getAllServices(""); 
} 



}