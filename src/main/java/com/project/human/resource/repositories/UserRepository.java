package com.project.human.resource.repositories;

import com.project.human.resource.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional 
@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    User findByName(String name);
}


