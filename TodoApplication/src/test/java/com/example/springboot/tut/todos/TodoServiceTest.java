package com.example.springboot.tut.todos;

import com.example.springboot.tut.error.NotFoundException;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
public class TodoServiceTest {
    @MockBean
    private TodoRepository todoRepository;
    @Autowired
    private TodoService todoService;

    @TestConfiguration
    static class TodoServiceContextConfiguration{
        @Bean
        TodoService todoService(){
            return new TodoService();
        }
    }

    @Test
    public void findAllTest(){
        Todo todo1 = new Todo(1L,"ft","first task description");
        Todo todo2 = new Todo(2L,"st","second task description");
        List<Todo> data = Arrays.asList(todo1,todo2);

        given(todoRepository.findAll()).willReturn(data);
        assertThat(todoService.findAll()).hasSize(2).contains(todo1,todo2);

    }

    @Test
    public void getBYId(){
        Todo todo1 = new Todo(1L,"ft","first task description");

        given(todoRepository.findById(anyLong())).willReturn(Optional.ofNullable(todo1));

        Todo result = todoService.getById(1L);
        assertThat(result.getTitle()).containsIgnoringCase("ft");
    }

    @Test(expected = NotFoundException.class)
    public void saveTest(){

        given(todoRepository.findById(anyLong())).willReturn(Optional.empty());

        todoService.getById(1L);
     }


}
