package by.mycom.ita.controller;

import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.impl.HotelServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void whenCreate_thenOk() {
        Hotel hotel = Hotel.builder()
                .name("Mercuri")
                .location("Egypt")
                .convenience("FiveStars")
                .build();

        mockMvc.perform(post("/hotel/create")
                        .content(objectMapper.writeValueAsString(hotel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mercuri"));
    }

    @Test
    @SneakyThrows
    void createNewHotel_thenThrowException() {
        Hotel hotel = Hotel.builder()
                .name("Mercuri")
                .build();
        mockMvc.perform(post("/hotel/create")
                        .content(objectMapper.writeValueAsString(hotel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataIsIncorrectException))
                .andExpect(result -> assertEquals("Data is not correct", result.getResolvedException().getMessage()));
    }

    @Test
    @SneakyThrows
    void whenReadAll_thenOk() {
        mockMvc.perform(get("/hotel/read/all"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void whenReadById_thenOk() {
        long id = 1L;
        mockMvc.perform(get("/hotel/read/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mercuri"));
    }

    @Test
    @SneakyThrows
    void whenUpdate_thenOk() {
        long id = 1L;
        Hotel hotel = new Hotel();
        hotel.setName("Alladin");
        mockMvc.perform(
                        put("/hotel/update/{id}", id)
                                .content(objectMapper.writeValueAsString(hotel))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alladin"));
    }

    @Test
    @SneakyThrows
    void updateHotel_thenNotFoundData(){
        long id = 2L;
        Hotel hotel = new Hotel();
        mockMvc.perform(
                        put("/hotel/update/{id}", id)
                                .content(objectMapper.writeValueAsString(hotel))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataNotFoundException));
    }
}