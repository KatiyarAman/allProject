package com.db.multi.service;

import com.db.multi.mongo.UserDetails;

public interface UserDetailService {
    UserDetails save(UserDetails userDetails);
    UserDetails getById(Long id);
}
