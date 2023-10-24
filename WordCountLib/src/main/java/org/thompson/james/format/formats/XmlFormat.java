/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.thompson.james.format.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.thompson.james.format.IFormat;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James Thompson
 */
public class XmlFormat implements IFormat<String> {
    
    private final XmlMapper xmlMapper;
    
    public XmlFormat() {
        xmlMapper = new XmlMapper();
    }

    @Override
    public String format(String input) {
        try {
            return xmlMapper.writeValueAsString(input);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(XmlFormat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
}
