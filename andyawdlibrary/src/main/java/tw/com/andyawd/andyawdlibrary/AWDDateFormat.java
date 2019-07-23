package tw.com.andyawd.andyawdlibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by andydai on 2019/5/10
 */

public class AWDDateFormat {

    public static AWDDateFormat getInstance() {
        return AWDDateFormatHolder.awdDateFormat;
    }

    private static class AWDDateFormatHolder {
        private static final AWDDateFormat awdDateFormat = new AWDDateFormat();
    }

    /**
     * 取得轉換後的日期格式
     * @param inputDate 輸入的日期
     * @param inputDatePattern 輸入的日期格式
     * @param youWantDatePattern 你想要的日期格式
     * @param locale 語系
     */
    public String getDateFormat(String inputDate, String inputDatePattern, String youWantDatePattern, Locale locale) {

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputDatePattern, locale);
        SimpleDateFormat youWantDateFormat = new SimpleDateFormat(youWantDatePattern, locale);

        try {
            Date transformDate = inputDateFormat.parse(inputDate);
            return youWantDateFormat.format(transformDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 取得轉換後的日期格式，預設台灣語系
     * @param inputDate 輸入的日期
     * @param inputDatePattern 輸入的日期格式
     * @param youWantDatePattern 你想要的日期格式
     */
    public String getDateFormat(String inputDate, String inputDatePattern, String youWantDatePattern) {

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputDatePattern, Locale.TAIWAN);
        SimpleDateFormat youWantDateFormat = new SimpleDateFormat(youWantDatePattern, Locale.TAIWAN);

        try {
            Date transformDate = inputDateFormat.parse(inputDate);
            return youWantDateFormat.format(transformDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
