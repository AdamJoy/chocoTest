package com.chrisaj.chocotest.tool;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberTool {

    public static String changeNumberWithComma(int number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
}
