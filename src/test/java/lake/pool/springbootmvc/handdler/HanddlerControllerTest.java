package lake.pool.springbootmvc.handdler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HanddlerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void events() throws Exception{
        mockMvc.perform(get("/handdles/1;name=lake"))
                .andDo(print())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("lake"));

    }

    @Test
    public void events2() throws Exception{
        mockMvc.perform(get("/owners/42;q=11;r=12/pets/21;q=22;s=23"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void params() throws Exception{
        mockMvc.perform(post("/params?name=lake&limit=2"))
                .andDo(print())
                .andExpect(jsonPath("name").value("lake"))
                .andExpect(jsonPath("limit").value(2));

    }

    @Test
    public void forms() throws Exception{
        mockMvc.perform(get("/handdles/form"))
                .andDo(print())
                .andExpect(view().name("events/form"))
                .andExpect(model().attributeExists("event"));

    }

    @Test
    public void modelAttribute() throws Exception{
        mockMvc.perform(post("/modelattribute?name=lake")
                    .param("limit", "-3"))
                .andDo(print())
                .andExpect(jsonPath("name").value("lake"));
//                .andExpect(jsonPath("limit").value(3))

    }

    @Test
    public void modelValided() throws Exception{
        mockMvc.perform(post("/modelValidated?name=lake")
                .param("limit", "-3"))
                .andDo(print())
                .andExpect(jsonPath("name").value("lake"));
//                .andExpect(jsonPath("limit").value(3))
        ;

    }

    @Test
    public void eventsValidatedForm() throws Exception{
        ResultActions result = mockMvc.perform(post("/modelValidatedForm")
                .param("name", "lake")
                .param("limit", "-3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());

        ModelAndView mav = result.andReturn().getModelAndView();
        Map<String, Object> model = mav.getModel();
        System.out.println("model.size() = " + model.size());

    }
}