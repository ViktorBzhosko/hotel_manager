package by.mycom.ita.controller;

import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.model.CommonUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommonUserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void createNewUser_thenOk() {
        CommonUser user = CommonUser.builder()
                .firstName("Viktor")
                .secondName("Bzhosko")
                .passport("ad12")
                .email("s@ru")
                .phoneNumber(1236)
                .build();

        mockMvc.perform(post("/users/create")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Viktor"));

    }

    @Test
    @SneakyThrows
    void createNewUser_thenException() {
        CommonUser user = CommonUser.builder()
                .secondName("Bzhosko")
                .passport("ad12")
                .email("s@ru")
                .phoneNumber(1236)
                .build();
        mockMvc.perform(post("/users/create")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataIsIncorrectException))
                .andExpect(result -> assertEquals("Data is not correct", result.getResolvedException().getMessage()));

    }
}