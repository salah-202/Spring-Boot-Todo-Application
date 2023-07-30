package com.example.springboot.tut.todos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Long> {
    Todo findByTitle(String title);
    List<Todo> findByUserId(Integer id);
}
