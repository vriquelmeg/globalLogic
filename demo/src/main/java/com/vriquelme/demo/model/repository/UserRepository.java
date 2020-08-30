package com.vriquelme.demo.model.repository;

import com.vriquelme.demo.model.entity.UserBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserBO, UUID> {

    UserBO findByEmail(String email);

}
