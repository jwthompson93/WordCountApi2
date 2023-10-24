/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.thompson.james.format.formats;

import org.thompson.james.format.IFormat;
import org.thompson.james.string.IFormattedString;

/**
 *
 * @author James Thompson
 */
public class FormattedStringFormat implements IFormat<IFormattedString> {

    @Override
    public String format(IFormattedString input) {
        return input.toFormattedString();
    }
}
