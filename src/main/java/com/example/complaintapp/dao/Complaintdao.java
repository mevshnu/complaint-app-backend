package com.example.complaintapp.dao;

import com.example.complaintapp.model.Complaint;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Complaintdao extends CrudRepository<Complaint,Integer>
{
@Query(value="SELECT `id`, `address`, `email`, `name`, `password`, `phone`, `username` FROM `complaints` WHERE `username`=:username AND `password`=:password")
    List<Complaint> FindUser(@Param("username")String username,@Param("password")String password);

@Query(value= "SELECT `id`, `address`, `email`, `name`, `password`, `phone`, `username` FROM `complaints` WHERE `id`=:id",nativeQuery = true)
  List<Complaint> FindUsers(@Param("id")Integer id);
}
