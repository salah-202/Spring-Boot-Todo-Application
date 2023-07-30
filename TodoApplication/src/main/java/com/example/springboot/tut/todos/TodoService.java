package com.example.springboot.tut.todos;

import com.example.springboot.tut.error.ConflictException;
import com.example.springboot.tut.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.geom.NoninvertibleTransformException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll(){
        return (List<Todo>) todoRepository.findAll();
    }

    public List<Todo> findByUser(Integer id){
        return (List<Todo>) todoRepository.findByUserId(id);
    }

    public Todo getById(Long id){
        try {
            return todoRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new NotFoundException("there's no task for this ID "+id);
        }
    }

    public Todo save(Todo todo){
        if(todoRepository.findByTitle(todo.getTitle()) != null){
            throw new ConflictException("there's a task with the same name");
        }
        return todoRepository.save(todo);
    }

    public void delete(Long id){
        todoRepository.deleteById(id);
    }


}
