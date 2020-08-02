package lake.pool.springbootmvc.handdler;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaActivator;
import org.apache.tika.detect.Detector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/files")
    public String fildUploadForm(Model model){
        return "/files/index";
    }

    @PostMapping("/files")
    public String fileUpload(@RequestParam MultipartFile file,
                             RedirectAttributes attributes){
        //스토리지 서비스 구현은 생략 ( 구글 / 아마존 / 드랍박스 / 로컬저장소 )
        //save 완료라고 치고~~
        String message = file.getOriginalFilename() + " is uploaded";
        attributes.addFlashAttribute("message", message);

        return "redirect:/files";
    }

//    @Bean
//    public Tika tika(){
//        return new Tika();
//    }

    @Autowired
    private Tika tika;
    /*
    ResponseEntity
     - 응답상태코드
     - 응답헤더
     - 응답본문
     */
    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        File file = resource.getFile();

//        Tika tika = new Tika(); //bean으로 생성 추천
        String mediaType = tika.detect(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() +"\"") //파일 다운로드시 기본적인 파일 이름
//                .header(HttpHeaders.CONTENT_TYPE,"image/jpg") //파일이 어떤 종류 인지 (file media type) // media type은 TIKA라는 라이브러리로 처리가능
                .header(HttpHeaders.CONTENT_TYPE,mediaType) //파일이 어떤 종류 인지 (file media type) // media type은 TIKA라는 라이브러리로 처리가능
                .header(HttpHeaders.CONTENT_LANGUAGE, file.length()+"") //파일크기
                .body(resource);
    }

//    @GetMapping("/files/onlyresource/{filename}")
//    @ResponseBody
//    public Resource fileDownloadResource(@PathVariable String filename) throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:" + filename);
//        return resource;
//    }
}
