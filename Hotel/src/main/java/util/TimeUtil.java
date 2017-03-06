package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

        public static String getCurrentTime() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(new Date());
        }
        
        public static boolean isBeforeLastYear(String date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                        Date d = sdf.parse(date);
                        Date now = new Date();
                        long days = (now.getTime() - d.getTime()) / (1000 * 3600 * 24);
                        return days > 365;
                } catch (ParseException e) {
                        e.printStackTrace();
                }
                return false;
        }
}
