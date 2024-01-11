package uk.gov.homeoffice.springtechtest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.gov.homeoffice.springtechtest.models.dto.UserPayload;
import uk.gov.homeoffice.springtechtest.models.entities.User;
import uk.gov.homeoffice.springtechtest.repository.UserRepository;
import uk.gov.homeoffice.springtechtest.service.UserService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

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
}
