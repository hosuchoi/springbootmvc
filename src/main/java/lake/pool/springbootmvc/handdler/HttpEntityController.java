package lake.pool.springbootmvc.handdler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/request")
public class HttpEntityController {

//    @Autowired
//    private EventValidator eventValidator;

    //Controller 내의 바인더 설정 ( 모든 핸들러 적용됨 )
    @InitBinder  // ("event") << 특정 모델만 체크하도록 설정도 가능
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id"); //필드값("id")을 받고 싶지 않을때 걸러낼수 있음
//    1)커스텀 유효성 체크
        webDataBinder.addValidators(new EventValidator());
    }

    /*
    모든 controller내의 핸들러들이 공통적으로 참고해야하는 모델 정보
     */
    @ModelAttribute
    public void categories(Model model){
        model.addAttribute("categories", List.of("study","seminar","hobby"));
    }
//    @ModelAttribute("categories")
//    public List<String> categories(){
//        return List.of("study","seminar","hobby");
//    }

    /*
    DataBinder : @InitBinder
     */

    /*
    *
    RequestBody 와 HttpEntity
    * *
     */
    @PostMapping("/requestbody")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, BindingResult bindingResult, @RequestHeader HttpHeaders headers, WebRequest request){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(System.out::println);
//            bindingResult.getAllErrors().forEach(error -> System.out.println("error = " + error));
        }
        MediaType contentType = headers.getContentType();
        System.out.println("contentType = " + contentType);

//        2)bean을 통한 커스텀한 유효성 체크
//        eventValidator.validate(event, bindingResult);

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
