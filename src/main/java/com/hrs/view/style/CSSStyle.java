package com.hrs.view.style;

import com.hrs.view.util.FieldValue;

public class CSSStyle
{
    public static String fontFamily(String font)
    {
        return "-fx-font-family: " + font + ";";
    }
    
    public static String VBoxCSS()
    {
        return fontFamily(FieldValue.FONT_MONACO)
                .concat(fontSize(FieldValue.FONT_SIZE_17))
                .concat(fontColor(FieldValue.FONT_COLOR_BLACK))
                .concat(fontPadding(FieldValue.FONT_PADDING_7));
    }
    
    public static String fontSize(Integer size)
    {
        return "-fx-font-size: " + size + ";";
    }
    
    public static String fontColor(String color)
    {
        return "-fx-font-color: " + color + ";";
    }
    
    public static String fontPadding(Integer size)
    {
        return "-fx-padding: " + size + ";";
    }
}
