package com.example.springboot.tut;

import com.example.springboot.tut.security.AppUser;
import com.example.springboot.tut.security.SignInRequest;
import com.example.springboot.tut.security.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AbstractTodoAppTest {

    private final String USERNAME_FOR_TEST = "salah@example.com";
    private final String PASSWORD_FOR_TEST = "123456";
    private final String AUTH_HEADER = "Authorization";

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setup(){
        final AppUser user = new AppUser("salah",USERNAME_FOR_TEST,new BCryptPasswordEncoder().encode(PASSWORD_FOR_TEST));
        user.setId(111);

        given(userService.loadUserByUsername(user.getUsername())).willReturn(user);
    }

    public ResultActions login(String username,String password) throws Exception{
        SignInRequest signInRequest = new SignInRequest(username,password);

        return mockMvc.perform(
                post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signInRequest))
        );
    }

    public MockHttpServletRequestBuilder doGet(String url){
        return get(url).header(AUTH_HEADER,getHeader());
    }

    public MockHttpServletRequestBuilder doPost(String url){
        return post(url).header(AUTH_HEADER,getHeader());
    }

    private String getHeader() {
        try {
            MvcResult result = login(USERNAME_FOR_TEST,PASSWORD_FOR_TEST).andReturn();
            String token = JsonPath.read(result.getResponse().getContentAsString(),"$.token");
            String header = String.format("Bearer %s",token);
            return header;

        }catch (Exception ex){
            return null;
        }
    }


}
