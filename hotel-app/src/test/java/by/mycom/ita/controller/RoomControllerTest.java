package by.mycom.ita.controller;

import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Room;
import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.Comfort;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelServiceImpl hotelService;

    @Test
    @SneakyThrows
    void whenUpdate_thenOk() {
        Room room = new Room();
        room.setId(1L);
        room.setNumberOfRoom(2);
        mockMvc.perform(
                        put("/room/update")
                                .content(objectMapper.writeValueAsString(room))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfRoom").value(2));
    }

    @Test
    @SneakyThrows
    void updateRoom_thenNotFoundData() {
        Room room = new Room();
        room.setId(2L);
        mockMvc.perform(
                        put("/room/update")
                                .content(objectMapper.writeValueAsString(room))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DataNotFoundException));
    }

    @Test
    @SneakyThrows
    void whenReadById_thenOk() {
        long id = 1L;
        mockMvc.perform(get("/room/find/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfRoom").value(2));
    }

    @Test
    @SneakyThrows
    void whenDeleteById_thenOk() {
        long id = 1L;
        mockMvc.perform(delete("/room/delete/{id}", id))
                .andExpect(status().isOk());
    }

    private Room getRoom() {
        return Room.builder()
                .numberOfRoom(3)
                .comfort(Comfort.STANDARD)
                .accommodation(Accommodation.SINGLE)
                .build();
    }

}