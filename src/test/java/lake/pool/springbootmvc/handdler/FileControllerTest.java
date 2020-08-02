package lake.pool.springbootmvc.handdler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void fileUploadTest() throws Exception{
        //테스트용 파일 생성
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt","text/plain", "hello file".getBytes());

        mockMvc.perform(multipart("/files").file(multipartFile))
                .andDo(print())
                .andExpect(status().is3xxRedirection()); //리다이렉트 상태인가?
    }


}