package com.demo.taskproject.repository;

import com.demo.taskproject.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    public Optional<Users> findByEmail(String email);

    @NativeQuery("select * from users where name = :input ")
    public Users findByUserName(@Param("input") String name);

    //HQl Query
    @Query(value = "from Users where name = :input")
    public Users findByUsername(@Param("input") String name);
}
