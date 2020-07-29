package lake.pool.springbootmvc.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Documented // Java Doc에 코멘트 남도록 설정
@Target(ElementType.METHOD) // 메서드에만 적용하겠다!
@Retention(RetentionPolicy.RUNTIME) // 클래스파일 로드해도 사라지지 않고 runtime시에도 유지처리됨
//@Retention(RetentionPolicy.CLASS) // 클래스로더가 클래스파일을 byte코드로 로딩하는 순간 사라짐
//@Retention(RetentionPolicy.SOURCE) // 주석 수준
@RequestMapping(method = RequestMethod.GET, value = "/custom")
public @interface GetHelloMapping {
}
