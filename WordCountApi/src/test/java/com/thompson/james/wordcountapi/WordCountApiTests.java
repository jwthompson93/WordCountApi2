package com.thompson.james.wordcountapi;

import com.thompson.james.wordcountapi.controller.TextAnalysisController;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author James Thompson
 */
@WebMvcTest(TextAnalysisController.class)
public class WordCountApiTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void Test_ping() throws Exception {
        this.mockMvc.perform(get("/api/textanalysis/ping")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Pong!")));
    }
    
    @Test
    void Test_FileUploaded_AnalysedSuccessfully() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello world & good morning. The date is 18/05/2016".getBytes());
        
        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/textanalysis/file/process/json").file(file))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"wordAmount\":9,\"averageWordLength\":4.55556,\"numberOfWordsOfLength\":{\"1\":1,\"2\":1,\"3\":1,\"4\":2,\"5\":2,\"7\":1,\"10\":1},\"highestOccurringWordLength\":{\"4\":2,\"5\":2}}"));
    }
    
    @Test
    void Test_FileRejected_ContentsEmpty_TextResponse() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "".getBytes());
        
        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/textanalysis/file/process/text").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("File is empty"));
    }
    
    
    @Test
    void Test_FileRejected_IncorrectFileExtension_TextResponse() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test.docx",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello world & good morning. The date is 18/05/2016".getBytes());
        
        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/textanalysis/file/process/text").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("File must be .txt file"));
    }
    
    @Test
    void Test_FileRejected_ContentsEmpty_JsonResponse() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "".getBytes());
        
        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/textanalysis/file/process/json").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{ \"code\":\"404\", \"message\":\"File is empty\" }"));
    }
    
    
    @Test
    void Test_FileRejected_IncorrectFileExtension_JsonResponse() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test.docx",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello world & good morning. The date is 18/05/2016".getBytes());
        
        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/textanalysis/file/process/json").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{ \"code\":\"404\", \"message\":\"File must be .txt file\" }"));
    }
}
