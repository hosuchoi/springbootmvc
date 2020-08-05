package lake.pool.springbootmvc.handdler;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class ResponseEntityController {

    @ExceptionHandler
    public String eventErrorHandler(EventException exception, Model model){
        model.addAttribute("message", "evnet error");

        return "/error";
    }

    @ExceptionHandler
    public String runtimeException(RuntimeException exception, Model model){
        model.addAttribute("message", "runtime error");

        return "/error";
    }


//    @ExceptionHandler({EventException.class,RuntimeException.class}) //두개의 exception을 동시에 처리 가능
//    public String eventErrorHandler(RuntimeException exception, Model model){ //단 두개중 상위의 exception을 파라미터로 받아야함.
//        model.addAttribute("message", "evnet error");
//
//        return "/error";
//    }

    @ExceptionHandler
    public ResponseEntity errorHandler(RuntimeException exception, Model model){ //rest api에서는 주로 리턴타입으로 ResponseEntity를 사용한다
        return ResponseEntity.badRequest().body("can't create event as ...");
    }

    @GetMapping("/exceptionhandler")
    public ResponseEntity<Event> exceptionTest(@Valid @RequestBody Event event, BindingResult bindingResult){
        throw new EventException(); // 위에 두개의 exception이 있더라도 가장 구체적인 exception을 탐

//        return ResponseEntity.ok(event);
    }

    @PostMapping("/responseEntity")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(event);
    }
}
