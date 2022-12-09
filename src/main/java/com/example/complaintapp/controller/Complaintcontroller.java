package com.example.complaintapp.controller;

import com.example.complaintapp.dao.Complaintdao;
import com.example.complaintapp.model.Complaint;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class Complaintcontroller {
    @Autowired
    private Complaintdao dao;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/userreg",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> UserRegistration(@RequestBody Complaint c)
    {
        System.out.println(c.getName().toString());
        System.out.println(c.getEmail().toString());
        System.out.println(c.getAddress().toString());
        System.out.println(c.getUsername().toString());
        System.out.println(c.getPhone().toString());
        dao.save(c);
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins ="*")
    @PostMapping(path="/userlogin",produces = "application/json",consumes = "application/json")
    public HashMap<String,String> Userlogin(@RequestBody Complaint co)
    {
        System.out.println(co.getEmail());
        List<Complaint> result = (List<Complaint>) dao.FindUser(co.getUsername(), co.getPassword());
        HashMap<String,String> map=new HashMap<>();
        if(result.size()==0)
        {
            map.put("status","failed");
        }
        else
        {
            int id = result.get(0).getId();
            map.put("userId",String.valueOf(id));
            map.put("status","success");
        }
        return map;


    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/getUserById", produces = "application/json", consumes = "application/json")
    public List<Complaint> GetUserById(@RequestBody Complaint um){
        return (List<Complaint>) dao.FindUsers(um.getId());
    }


}
