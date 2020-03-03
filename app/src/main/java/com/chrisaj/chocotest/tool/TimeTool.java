package com.chrisaj.chocotest.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeTool {

    // 時間格式轉換 2017-10-21T12:34:41.000Z"" -> "yyyy-MM-dd HH:mm:ss"
    public static String TransformTimeFormat(String originalTime) {

        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat resultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = originalFormat.parse(originalTime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        String formatted = resultFormat.format(d);
        return formatted;
    }
}
