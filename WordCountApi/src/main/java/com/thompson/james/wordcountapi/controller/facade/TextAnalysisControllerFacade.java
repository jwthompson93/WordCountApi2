/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thompson.james.wordcountapi.controller.facade;

import java.io.InputStream;
import org.springframework.stereotype.Component;
import org.thompson.james.file.TextFileReader;
import org.thompson.james.process.TextAnalysisProcess;

/**
 *
 * @author James Thompson
 */
@Component("textAnalysisControllerFacade")
public class TextAnalysisControllerFacade {
    private TextAnalysisProcess textAnalysisProcess;
    
    public TextAnalysisControllerFacade() {
        this.textAnalysisProcess = new TextAnalysisProcess();
    }
    
    public String ProcessFileToOutputtedFormat(InputStream inputStream, String outputType) {
        TextFileReader fileReader = new TextFileReader();
        String text = fileReader.getTextFromFile(inputStream);
        
        String responseString = textAnalysisProcess.process(text, outputType);
        return responseString;
    }
}
