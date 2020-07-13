package lake.pool.springbootmvc.sample;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RequestMappingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void requestMapping() throws Exception{
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

        mockMvc.perform(post("/hi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

        mockMvc.perform(put("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void uriTest() throws Exception{
        mockMvc.perform(get("/uriTestA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("uri test0"))
        ;
        mockMvc.perform(get("/uriTest/test1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("uri test1"))
        ;

        mockMvc.perform(get("/uriTest/test2/2222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("uri test2"))
        ;

        mockMvc.perform(get("/uriTestGgu/lake"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("uri lake"))
        ;
    }

    @Test
    public void mediaTypeTest() throws Exception{
        mockMvc.perform(get("/contents")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void headAndParamTest() throws Exception{
        mockMvc.perform(get("/headers")
                    .header(HttpHeaders.FROM,"localhost")
                    .param("name", "lake"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void headAndOptinHttpMethodTest() throws Exception{
        mockMvc.perform(head("/head"))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(options("/head"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void customAnnotationsTest() throws Exception{
        mockMvc.perform(head("/custom"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}