package lake.pool.springbootmvc.handdler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HanddlerController {

    @GetMapping("/handdles/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Optional<Integer> id, @MatrixVariable String name){
        Integer integer = id.orElse(null);
//        Integer integer1 = id.orElseThrow();
        Event event = new Event();
        event.setId(integer);
        event.setName(name);

        return event;
    }

    // GET /owners/42;q=11;r=12/pets/21;q=22;s=23
    @GetMapping("/owners/{ownerId}/pets/{petId}")
    @ResponseBody
    public void findPet(
            @MatrixVariable MultiValueMap<String, String> matrixVars,
            @MatrixVariable(pathVar = "petId") MultiValueMap<String, String> petMatrixVars
            ){
        // matrixVars = {q=[11, 22], r=[12], s=[23]}
        System.out.println("matrixVars = " + matrixVars);
        // petMatrixVars = {q=[22], s=[23]}
        System.out.println("petMatrixVars = " + petMatrixVars);

    }

    @PostMapping("/params")
    @ResponseBody
    public Event getEvent(@RequestParam String name, @RequestParam Integer limit){
        Event event = new Event();
        event.setName(name);
        event.setLimit(limit);

        return event;
    }

    @GetMapping("/handdles/form")
    public String eventsForm(Model model){
        Event e = new Event();
        e.setLimit(50);
        model.addAttribute("event", e);
        return "/events/form";
    }

    /*
    ModelAttribute 방식의 이상적인 방법
     */
    @PostMapping("/modelattribute")
    @ResponseBody                      //@Valid의 결과 : Field error in object 'event' on field 'limit': rejected value [-3];
    public Event eventsModelAttribute(@Valid @ModelAttribute Event event, BindingResult bindingResult){ // @Valid : 바인딩 된 후에 유효성 체크 결과를 BindingResult에 담아줌 , BindingResult : 바인딩 에러 exception 처리됨
        if(bindingResult.hasErrors()){
            System.out.println("====================");
//            아래와 같이 limit 값 타입이 맞지 않다고 바인딩 exception을 담아준다.
//            Field error in object 'event' on field 'limit': rejected value [dasdsdss]; codes [typeMismatch.event.limit,typeMismatch.limit,typeMismatch.java.lang.Integer,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [event.limit,limit]; arguments []; default message [limit]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'limit'; nested exception is java.lang.NumberFormatException: For input string: "dasdsdss"]
            bindingResult.getAllErrors().forEach( c -> {
                System.out.println(c.toString());
            });
        }

        return event;
    }

    /*
    @Validated : @Valid 기능 + group 지정 가능 ( @Valid는 group 지정이 불가능하다 )
    */
    @PostMapping("/modelValidated")
    @ResponseBody               // Event.ValidateName.class 그룹으로 선언한 유효성 체크만 한다
    public Event eventsValidated(@Validated(Event.ValidateName.class) @ModelAttribute Event event, BindingResult bindingResult){ // @Valid : 바인딩 된 후에 유효성 체크 결과를 BindingResult에 담아줌 , BindingResult : 바인딩 에러 exception 처리됨
        if(bindingResult.hasErrors()){
            System.out.println("====================");
//            아래와 같이 limit 값 타입이 맞지 않다고 바인딩 exception을 담아준다.
//            Field error in object 'event' on field 'limit': rejected value [dasdsdss]; codes [typeMismatch.event.limit,typeMismatch.limit,typeMismatch.java.lang.Integer,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [event.limit,limit]; arguments []; default message [limit]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'limit'; nested exception is java.lang.NumberFormatException: For input string: "dasdsdss"]
            bindingResult.getAllErrors().forEach( c -> {
                System.out.println(c.toString());
            });
        }

        return event;
    }

    /*
    Thymeleaf를 통한 validated 처리
    */
    @PostMapping("/modelValidatedForm")
    public String eventsValidatedForm(@Validated @ModelAttribute Event event, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/events/form";
        }

//        List<Event> eventList = new ArrayList<>();
//        eventList.add(event);
//        model.addAttribute(eventList); // == model.addAttribute("eventList", eventList);
        return "redirect:/events/list";  // post 처리의 경우 웹사이트 reflash시에  중복 submit이 발생하지 않도록 get 매핑을 redirect 처리
    }

    /*
    redirect 할 get 매핑
    */
    @GetMapping("/events/list")
    public String getEvents(Model model){
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        model.addAttribute(eventList); // == model.addAttribute("eventList", eventList);

        return "/events/list";
    }
}
