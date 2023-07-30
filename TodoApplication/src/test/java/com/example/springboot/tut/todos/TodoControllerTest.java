package com.example.springboot.tut.todos;

import com.example.springboot.tut.AbstractTodoAppTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;


public class TodoControllerTest extends AbstractTodoAppTest {
    @MockBean
    private TodoService todoService;


    @Test
    public void getAllTodosTest() throws Exception {
        Todo todo1 = new Todo(1L,"ft","first task description");
        Todo todo2 = new Todo(2L,"st","second task description");
        List<Todo> data = Arrays.asList(todo1,todo2);

        given(todoService.findByUser(anyInt())).willReturn(data);

        mockMvc.perform(doGet("/api/v1/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    public void CreateNewTodoTest() throws Exception {
        Todo todo1 = new Todo();
        todo1.setTitle("Title of todo");
        todo1.setDescription("Title of todo");


        given(todoService.save(Mockito.any(Todo.class))).willReturn(todo1);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(doPost("/api/v1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(todo1))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",is(todo1.getTitle())));

    }
}
