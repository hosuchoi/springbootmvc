package lake.pool.springbootmvc.handdler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class VisitTimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("visitTime") == null){
            session.setAttribute("visitTime", LocalDateTime.now());
        }

        return true; // true 처리해야 다음 인터셉터나 핸들러까지 처리가 가능함
    }


}
