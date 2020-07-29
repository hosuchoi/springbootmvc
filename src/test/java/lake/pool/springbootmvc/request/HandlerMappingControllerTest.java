package lake.pool.springbootmvc.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HandlerMappingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEvents() throws Exception{
        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event"))
        ;
    }

    @Test
    public void getEventsWithId() throws Exception{
        mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event"))
        ;

        mockMvc.perform(get("/events/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event"))
        ;
        mockMvc.perform(get("/events/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event"))
        ;
    }

    @Test
    public void createEvents() throws Exception{
        mockMvc.perform(post("/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void deleteEvents() throws Exception{
        mockMvc.perform(delete("/events/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event"))
        ;

        mockMvc.perform(delete("/events/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event"))
        ;
        mockMvc.perform(delete("/events/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("event"))
        ;
    }

    @Test
    public void updateEvents() throws Exception{
        mockMvc.perform(put("/events")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }
}