package com.example.springboot.tut.todos;

import com.example.springboot.tut.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController extends BaseController {

    @Autowired
    private TodoService todoService;
    @GetMapping(value = {"","/"})
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> result =  todoService.findByUser(getCurrentUser().getId());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){

        Todo result = todoService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"","/"})
    public ResponseEntity<Todo> CreateNewTodo(@Valid @RequestBody Todo todo){
        todo.setUserId(getCurrentUser().getId());
        Todo result = todoService.save(todo);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){

        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
