package com.db.multi.mongo.repo;

import com.db.multi.mongo.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDetails,String> {
}
