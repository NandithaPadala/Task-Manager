package com.demo.taskproject.repository;

import com.demo.taskproject.entity.Task;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Task SET taskname = :taskname WHERE id = :id")
    public Task updateTaskName(@Param("taskname") String taskname, @Param("id") long id);

}
