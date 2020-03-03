package com.chrisaj.chocotest.tool;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberTool {

    // 將數字 增加Comma標示
    public static String changeNumberWithComma(int number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }
}
