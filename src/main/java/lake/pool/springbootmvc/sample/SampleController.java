package lake.pool.springbootmvc.sample;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    /*
    method 생략시 : 모든 메소드 타입 가능 ( get,post,put,delete,fatch 등...)
     */
    @RequestMapping(
            value = "/hello",
            method = {RequestMethod.GET, RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, // Content Type : 요청에 대한 type
            produces = MediaType.TEXT_PLAIN_VALUE  // Accpt : 응답에 대한 type
            )
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
