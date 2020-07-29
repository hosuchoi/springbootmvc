package lake.pool.springbootmvc.request;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(
        method = RequestMethod.GET,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, //get에 대한 모든 요청을 json으로 받겠다!
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //get에 대한 모든 응답을 json으로 하겠다!
public class PostMappingController {
}
