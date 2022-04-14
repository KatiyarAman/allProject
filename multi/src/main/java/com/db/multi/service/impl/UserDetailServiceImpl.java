package com.db.multi.service.impl;

import com.db.multi.mongo.UserDetails;
import com.db.multi.mongo.repo.UserRepository;
import com.db.multi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails save(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    @Override
    public UserDetails getById(Long id) {
        return userRepository.findById(String.valueOf(id)).get();
    }
}
