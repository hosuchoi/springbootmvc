package lake.pool.springbootmvc.request;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HandlerMappingController {

    @GetMapping("/events")
    @ResponseBody
    public String events(){
        return "event";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String eventsWithId(@PathVariable int id){
        return "event";
    }

    @PostMapping(
            value = "/events",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public String createEvent(){
        return "event";
    }

    /*
    get이랑 비슷함
     */
    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String deleteEvents(@PathVariable int id){
        return "event";
    }

    /*
    post랑 비슷함
     */
    @PutMapping(
            value = "/events",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public String udpateEvent(){
        return "event";
    }
}
