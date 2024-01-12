package uk.gov.homeoffice.springtechtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.gov.homeoffice.springtechtest.models.dto.UserPayload;
import uk.gov.homeoffice.springtechtest.models.entities.User;
import uk.gov.homeoffice.springtechtest.service.UserService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTwoTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void given_whenGetUsers_ThenReturnEmptyList() throws Exception {


        UserPayload userPayload1 = new UserPayload(1L, "User 1");
        UserPayload userPayload2 = new UserPayload(2L, "User 2");

        List<UserPayload> emptyList = List.of(userPayload1, userPayload2);
        given(userService.getAllUsers()).willReturn(emptyList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(userPayload1.getName())));

        verify(userService).getAllUsers();
    }

    @Test
    public void given_whenNewUsersIsAdded_ThenReturnEmptyList() throws Exception {

        UserPayload userPayload1 = new UserPayload("User 5");
        UserPayload userPayload2 = new UserPayload(2L, "User 2");

        objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userPayload1);

//        List<UserPayload> emptyList = List.of(userPayload1, userPayload2);
        given(userService.createUser(any())).willReturn(userPayload1);


        System.out.println(json);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
//
        verify(userService).createUser(any());
    }
}
