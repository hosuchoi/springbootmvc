package lake.pool.springbootmvc.sample;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestMappingController {

    /*
    method 생략시 : 모든 메소드 타입 가능 ( get,post,put,delete,fatch 등...)
     */
    @RequestMapping(
            value = {"/hello", "/hi"},
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}
            )
    @ResponseBody
    public String hello(){
        return "hello";
    }

    /*
    URI 관련
     - ? : 문자 한개
     - /* : /한개
     - /** : /여러개
     */

    @RequestMapping(
            value = "/uriTest?"
    )
    @ResponseBody
    public String uriTest0(){
        return "uri test0";
    }
    @RequestMapping(
            value = "/uriTest/*"
    )
    @ResponseBody
    public String uriTest1(){
        return "uri test1";
    }

    @RequestMapping(
            value = "/uriTest/**"
    )
    @ResponseBody
    public String uriTest2(){
        return "uri test2";
    }

    @RequestMapping(
            value = "/uriTestGgu/{name:[a-z]+}"  //정규식 사용 가능 name값을 @PathVariable로 받을수 있다 ( name:[a-z]+ 문자만 받겟다)
    )
    @ResponseBody
    public String uriTest3(@PathVariable String name){
        return "uri " + name;
    }


    /*
    컨텐츠 타입 설정
     */
    @GetMapping( value = "/contents",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, // Content Type : 요청에 대한 type
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE  // Accept : 응답에 대한 type
    )
    @ResponseBody
    public String jsonTest(){
        return "test";
    }


    /*
    Header
    */
    @GetMapping( value = "/headers", headers = HttpHeaders.FROM // httpHeader의 from 값이 있는 경우에만 허용
            ,params = "name" // name이라는 파라미터가 있을때만 허용
    )
    @ResponseBody
    public String headersTest(){
        return "test";
    }

    /*
        head Http Method
        응답 head 정보만 보내줌 body는 안보내줌

        options Http Method
        서버나 uri의 리소스가 제공하는 기능을 확인용
        해당 서버가 살아 있는지 확인용( ping 을 보내는 행위 )
     */
    @GetMapping( value = "/head")
    @ResponseBody
    public String headTest(){
        return "test";
    }

    @PostMapping( value = "/head")   //응답 형태 Headers = [Allow:"POST,GET,HEAD,OPTIONS"]
    @ResponseBody
    public String optionsTest(){  
        return "test"; 
    }

    /*
    커스텀 http methos 어노테이션
     */
    @GetHelloMapping
    @ResponseBody
    public String customTest(){
        return "test";
    }
}
