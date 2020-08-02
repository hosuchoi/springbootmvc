package lake.pool.springbootmvc.handdler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@SessionAttributes({"sessionEvent","sessionEvent2","multiEvent"})
public class SessionController {

    /*
    Session Attributes는 특정 클래스 안에서만 처리 ( 하나의 콘트롤러 )  
    Session Attribute는 모든 클래스 안에서만 처리 ( 모든 콘트롤러외 필터,인터셉터등.. 다 사용 가능)
      단) Session Attributes도 전역에서 쓸수는 있지만, 그런 용도가 아님
     */
    /*
    session attributes row level 방식
     */
    @GetMapping("/session/lowlevel")
    public String sessionLow(Model model, HttpSession httpSession){ //HttpSession 직접 파라미터로 받아서 처리하는 방식은 가장 row level의 방식
        Event event = new Event();
        event.setLimit(50);
        model.addAttribute("event", event);
        //session attribute에 값 담기
        httpSession.setAttribute("event", event);

        return "/session/form";
    }

    /*
    session attributes 어노테이션 방식
    */
    @GetMapping("/session/anotation")
    public String sessionAno(Model model){
        Event event = new Event();
        event.setLimit(50);
        model.addAttribute("event", event);  // < session에 안담김
        //model 에 담긴 "sessionEvent" 와 @SessionAttributes("sessionEvent") 동일한 값이면 세션에도 해당 값을 담는다
        model.addAttribute("sessionEvent", event);  // < session에 담기
        model.addAttribute("sessionEvent2", event); // < session에 담기

        return "/session/form";
    }

    /*
    session complete : session 완료 > session 값을 비운다(세션정리)
    */
    @GetMapping("/session/compelete")
    public String sessionComplete(Model model, SessionStatus sessionStatus, HttpSession httpSession){
        Event event = new Event();
        event.setLimit(50);
        model.addAttribute("event", event);  // < session에 안담김
        //model 에 담긴 "sessionEvent" 와 @SessionAttributes("sessionEvent") 동일한 값이면 세션에도 해당 값을 담는다
        model.addAttribute("sessionEvent", event);  // < session에 담기
        model.addAttribute("sessionEvent2", event); // < session에 담기

        sessionStatus.setComplete(); // session 완료 : session 값을 비운다(세션정리)

        return "/session/form";
    }


    /*
    *
    여기서 부턴 세션을 이용한 멀티폼 서브밋
    *
    */
    @GetMapping("/session/name")
    public String sessionName(Model model){
        model.addAttribute("multiEvent", new Event());
        return "/session/form-name";
    }

    @PostMapping("/session/form/name")
    public String sessionNameSubmit(@Validated @ModelAttribute Event event,
                                    Model model,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/session/form-name";
        }
        return "redirect:/session/limit";
    }

    @GetMapping("/session/limit")
    public String sessionLimit(@ModelAttribute Event event, Model model){
        model.addAttribute("multiEvent", event);
        return "/session/form-limit";
    }

    @PostMapping("/session/form/limit")
    public String sessionLimitSubmit(@Validated @ModelAttribute Event event,
                                     BindingResult bindingResult,
                                     SessionStatus sessionStatus,
                                     RedirectAttributes redirectAttributes){ // RedirectAttributes 리다이렉트 할때 모델 정보을 넘겨줌
//        if(bindingResult.hasErrors()){
//            return "/session/form-limit";
//        }
        sessionStatus.setComplete();

        /*
        RedirectAttributes.addAttribute : Uri의 파라미터로 붙혀서 데이터 전달 ( localhost:8080?name=lake&limit=1000 ) 그래서 모든 데이터가 string으로 변환가능해야함. ( event객체 못담음 )
        RedirectAttributes.addFlashAttribute : http session에 값을 저장해서 uri경로에 노출안됨. 1회성으로 1번 읽고 쓰면 사라짐. 모든 데이터 가능 ( event객체 담을수 있음 )
         */
        redirectAttributes.addAttribute("name", event.getName());
        redirectAttributes.addAttribute("limit", event.getLimit());
        redirectAttributes.addFlashAttribute("newEvent", event);

        return "redirect:/events/list";
    }

    /*
    Session Attribute
     */
    @GetMapping("/session/visit")
    public String sessionLimit(@RequestParam String name,
                               @RequestParam String limit,
                               Model model,
                               @SessionAttribute("visitTime") LocalDateTime visitTime){
        System.out.println("visitTime = " + visitTime);
        model.addAttribute("multiEvent", new Event());

        Event newEvent = (Event)model.asMap().get("newEvent"); //FlashAttribute를 통해 넣은 데이터를 Model 통해서 꺼냄
        System.out.println("newEvent = " + newEvent);

        return "/session/form";
    }

}
