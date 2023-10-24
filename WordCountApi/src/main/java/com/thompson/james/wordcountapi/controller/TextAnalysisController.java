package com.thompson.james.wordcountapi.controller;

import com.thompson.james.wordcountapi.controller.facade.TextAnalysisControllerFacade;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author James Thompson
 */
@RestController
@RequestMapping("/api/textanalysis")
public class TextAnalysisController {
    
    private TextAnalysisControllerFacade textAnalysisControllerFacade;
    
    public TextAnalysisController() {
        this.textAnalysisControllerFacade = new TextAnalysisControllerFacade();
    }
    
    @GetMapping("ping")
    public String ping() {
        return "Pong!";
    }
    
    @PostMapping(path = "file/process/json", 
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> ProcessFileToJson(
            @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.badRequest()
                    .body("{ \"code\":\"404\", \"message\":\"File is empty\" }");
        }
        if(!FilenameUtils.getExtension(file.getOriginalFilename()).equals("txt")){
            return ResponseEntity.badRequest()
                    .body("{ \"code\":\"404\", \"message\":\"File must be .txt file\" }");
        }
        
        String responseJson = textAnalysisControllerFacade
                .ProcessFileToOutputtedFormat(file.getInputStream(), "json");
        return ResponseEntity.ok(responseJson);
    }
    
    @PostMapping(path = "file/process/text", 
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
                produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ProcessFileToText(
            @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.badRequest()
                    .body("File is empty");
        }
        if(!FilenameUtils.getExtension(file.getOriginalFilename()).equals("txt")){
            return ResponseEntity.badRequest()
                    .body("File must be .txt file");
        }
        String responseJson = textAnalysisControllerFacade
                .ProcessFileToOutputtedFormat(file.getInputStream(), "text");
        return ResponseEntity.ok(responseJson);
    }
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIoException(IOException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Could not handle file");
    }
}
