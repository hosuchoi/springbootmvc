package lake.pool.springbootmvc.handdler;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//@Component
//커스텀한 유효성 체크
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event)target;
        if(event.getName().equalsIgnoreCase("aaa")){
            errors.rejectValue("name","wrongValue","the value is not allowed");
        }
    }
}
