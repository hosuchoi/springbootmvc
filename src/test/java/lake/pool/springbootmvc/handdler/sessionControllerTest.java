package lake.pool.springbootmvc.handdler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.websocket.Session;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest
public class sessionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void sessionAttribute로우레벨방식() throws Exception {
        MockHttpServletRequest request = mockMvc.perform(get("/session/lowlevel"))
                .andDo(print())
                .andExpect(view().name("/session/form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", notNullValue()))
                .andReturn().getRequest();

        Object event = request.getSession().getAttribute("event");
        System.out.println("event = " + event);
    }

    @Test
    public void sessionAttribute어노테이션방식() throws Exception {
        MockHttpServletRequest request = mockMvc.perform(get("/session/anotation"))
                .andDo(print())
                .andExpect(view().name("/session/form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", nullValue()))
                .andExpect(request().sessionAttribute("sessionEvent", notNullValue()))
                .andExpect(request().sessionAttribute("sessionEvent2", notNullValue()))
                .andReturn().getRequest();

        Object event = request.getSession().getAttribute("event");
        System.out.println("event = " + event);
        Object sessionEvent = request.getSession().getAttribute("sessionEvent");
        System.out.println("sessionEvent = " + sessionEvent);
        Object sessionEvent2 = request.getSession().getAttribute("sessionEvent2");
        System.out.println("sessionEvent2 = " + sessionEvent2);
    }


    @Test
    public void sessionAttributes세션완료() throws Exception {
        MockHttpServletRequest request = mockMvc.perform(get("/session/compelete"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("sessionEvent", nullValue()))
                .andExpect(request().sessionAttribute("sessionEvent2", nullValue()))
                .andReturn().getRequest();
    }

    @Test
    public void session안에담긴테스팅() throws Exception {
        Event newEvent = new Event();
        newEvent.setName("lake");
        newEvent.setLimit(10000);

        mockMvc.perform(get("/session/compelete")
                    .sessionAttr("visitTime", LocalDateTime.now()) //세션에 attribute 값
                    .flashAttr("newEvent", newEvent)) //세션에 flash attribute 값
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//p").nodeCount(2)); //xpath을 이용한 html 점검 : <p></p> p태크가 2개 있다?
    }
}