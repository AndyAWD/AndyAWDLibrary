package tw.com.andyawd.andyawdlibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AWDDateFormat {


    public String AWDDateFormat(String inputDate, String inputDatePattern, String youWantDatePattern, Locale locale) {

        //TODO
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputDatePattern, locale);
        SimpleDateFormat youWantDateFormat = new SimpleDateFormat(youWantDatePattern, locale);

        try {
            Date transformDate = inputDateFormat.parse(inputDate);
            return youWantDateFormat.format(transformDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return "00:00";
        }
    }
}
