package lake.pool.springbootmvc.handdler;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
전역 controller : 모든 controller에 공통으로 적용됨
- @InitBinder : 공통 커스텀 validted
- @ModelAttribute : 공통 모델정보
- @ExceptionHandler : 공통 exception 처리
 */
@ControllerAdvice(assignableTypes =  {HttpEntityController.class,ResponseEntityController.class}) //특정 controller에만 설정도 가능하다
//@RestControllerAdvice //@ControllerAdvice와 동일 단, 모든 handler에 ResponseBody가 자동 설정됨
public class GlobalController {

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

    @ExceptionHandler
    public String eventErrorHandler(EventException exception, Model model){
        model.addAttribute("message", "evnet error");

        return "/error";
    }
}
