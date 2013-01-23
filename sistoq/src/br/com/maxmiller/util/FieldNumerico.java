/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxmiller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author max
 */
public class FieldNumerico extends PlainDocument {

    private int iMaxLength;

    public FieldNumerico(int maxlen) {
        super();
        iMaxLength = maxlen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        Pattern pattern = Pattern.compile("\\d");

        Matcher matcher = pattern.matcher(str);
        if (str == null) {
            return;
        }

        if (iMaxLength <= 0 && matcher.find()) // aceitara qualquer no. de caracteres
        {
            super.insertString(offset, str, attr);
            return;
        }

        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLength && matcher.find()) // se o comprimento final for menor...
        {
            super.insertString(offset, str, attr);   // ...aceita str
        }
    }
}
