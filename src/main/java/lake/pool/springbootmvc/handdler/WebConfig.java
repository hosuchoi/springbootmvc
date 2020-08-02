package lake.pool.springbootmvc.handdler;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;

@Configuration
@EnableWebMvc //순수한 web mvc 설정
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //@MatrixVariable 설정을 위해서 세미콜론 삭제 막는 기능 추가
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false); // url paht에 세미콜론(;)을 삭제하지 마라
        configurer.setUrlPathHelper(urlPathHelper); 
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // interceptor 등록
        registry.addInterceptor(new VisitTimeInterceptor());
    }

    //추가할 컨버터가 있을 경우 사용
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        
    }

    //기본 컨버터는 미사용함
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        
//    }
}
