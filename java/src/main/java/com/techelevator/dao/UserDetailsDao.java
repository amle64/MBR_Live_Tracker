package com.techelevator.dao;

import com.techelevator.model.UserDetails;

import java.util.List;

public interface UserDetailsDao {

    List<UserDetails> getUserDetails();

    UserDetails createUserDetails(int userId);

    int updateUserDetails(UserDetails userDetails);

    UserDetails getUserDetailsByUsername(String username);

    UserDetails getUserDetailsByDetailId(int Id);

    UserDetails getUserDetailsByUserId(int id);
}
