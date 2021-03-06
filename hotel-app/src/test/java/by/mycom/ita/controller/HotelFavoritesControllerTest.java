package by.mycom.ita.controller;

import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.impl.HotelFavoritesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotelFavoritesControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelFavoritesService favoritesService;

    @Test
    @SneakyThrows
    void whenCreateFavorites_thenOk() {
        long userId = 1L;
        Hotel hotel = Hotel.builder()
                .id(1L)
                .name("Mercuri")
                .location("Egypt")
                .convenience("FiveStars")
                .build();

        mockMvc.perform(post("/favorites/create/{userId}", userId)
                        .content(objectMapper.writeValueAsString(hotel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hotel.name").value("Mercuri"));
    }

    @Test
    @SneakyThrows
    void whenReadAll_thenOk() {
        long id = 1L;
        Hotel hotel = Hotel.builder()
                .id(1L)
                .name("Mercuri")
                .location("Egypt")
                .convenience("FiveStars")
                .build();

        favoritesService.favorites(1L, hotel);

        mockMvc.perform(get("/favorites/read/all/{userId}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id));
    }
}