package com.ceniuch.bluediamondbackend.users;

import com.ceniuch.bluediamondbackend.users.dtos.CreateUserDto;
import com.ceniuch.bluediamondbackend.users.dtos.GetUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    public GetUserDto createNewUser(String uid, String username) throws Exception {
        CreateUserDto newUser = new CreateUserDto(uid, username);

        MvcResult postUserResult = mockMvc.perform(post("/users")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(newUser))
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isCreated())
                .andReturn();

        String newUserAsString = postUserResult.getResponse().getContentAsString();
        return mapper.readValue(newUserAsString, GetUserDto.class);
    }

    @Test
    public void getUsersReturnsSuccess() throws Exception {
        mockMvc.perform(get("/users")
                        .param("uid", createNewUser("test-uid-1", "test-user-1").userUid()))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserReturnsCreated() throws Exception {
        CreateUserDto newUser = new CreateUserDto("new-uid", "new-username");

        mockMvc.perform(post("/users")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userUid").value("new-uid"))
                .andExpect(jsonPath("$.username").value("new-username"));
    }

    @Test
    public void getUserWithInvalidUidReturnsNotFound() throws Exception {
        mockMvc.perform(get("/users")
                        .param("uid", "non-existent-uid"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createDuplicateUserReturnsBadRequest() throws Exception {
        createNewUser("test-uid-2", "test-user-2");

        CreateUserDto duplicateUser = new CreateUserDto("test-uid-2", "other-username");

        mockMvc.perform(post("/users")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(duplicateUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserWithEmptyUsernameReturnsBadRequest() throws Exception {
        CreateUserDto invalidUser = new CreateUserDto("UID", "");

        mockMvc.perform(post("/users")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(mapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUserWithMissingUidParamReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isBadRequest());
    }
}
