package lake.pool.springbootmvc.handdler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //@MatrixVariable 설정을 위해서 세미콜론 삭제 막는 기능 추가
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false); // url paht에 세미콜론(;)을 삭제하지 마라
        configurer.setUrlPathHelper(urlPathHelper); 
    }
}
