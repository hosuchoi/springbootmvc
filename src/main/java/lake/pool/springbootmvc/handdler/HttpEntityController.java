package lake.pool.springbootmvc.handdler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/request")
public class HttpEntityController {

    @PostMapping("/requestbody")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, BindingResult bindingResult, @RequestHeader HttpHeaders headers, WebRequest request){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(System.out::println);
//            bindingResult.getAllErrors().forEach(error -> System.out.println("error = " + error));
        }
        MediaType contentType = headers.getContentType();
        System.out.println("contentType = " + contentType);

        return ResponseEntity.ok().body(event);
    }

    /*
    HttpEntity는 requestBody와 비슷하지만 헤더 정보를 추가적으로 사용할 수 있다.
    */
    @PostMapping("/httpentity")
    public Event createEvent(HttpEntity<Event> request){  // HttpEntity는 @Valid 사용불가
        MediaType contentType = request.getHeaders().getContentType();
        System.out.println("contentType = " + contentType);

        return request.getBody();
    }

//    @PostMapping
//    public ResponseEntity<Event> create(@Valid @RequestBody Event, BindingResult result, @RequestHeader HttpHeaders headers, WebRequest request ) {
//        return ResponseEntity.ok().body(new Event());
//    }
}
