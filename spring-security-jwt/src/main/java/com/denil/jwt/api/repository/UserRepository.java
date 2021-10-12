package com.denil.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.denil.jwt.api.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
    User findByUserNameAndPassword(String uname,String pass);
    
}
	