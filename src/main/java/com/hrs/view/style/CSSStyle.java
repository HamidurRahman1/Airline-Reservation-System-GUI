package com.hrs.view.style;

import com.hrs.util.Utility;

public class CSSStyle
{
    public static String fontFamily(String font)
    {
        return "-fx-font-family: " + font + ";";
    }
    
    public static String VBoxCSS()
    {
        return fontFamily(Utility.FONT_MONACO)
                .concat(fontSize(Utility.FONT_SIZE_17))
                .concat(fontColor(Utility.FONT_COLOR_BLACK))
                .concat(fontPadding(Utility.FONT_PADDING_7));
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
