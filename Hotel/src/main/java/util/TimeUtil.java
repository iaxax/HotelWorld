package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

        public static String getCurrentTime() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(new Date());
        }
        
        public static String getCurrentDate() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        
        public static String getYear(String time) {
                String []temp = time.split(" ");
                String date = temp[0];
                temp = date.split("-");
                return temp[0];
        }
        
        public static String[] getLatest3Year() {
                String[] result = new String[3];
                Calendar c = Calendar.getInstance();
                int now = c.get(Calendar.YEAR);
                result[2] = String.valueOf(now);
                result[1] = String.valueOf(now - 1);
                result[0] = String.valueOf(now - 2);
                return result;
        }
        
        public static boolean isBeforeToday(String date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                        Calendar now = Calendar.getInstance();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(sdf.parse(date));
                        int nowDay = now.get(Calendar.DAY_OF_YEAR);
                        int nowYear = now.get(Calendar.YEAR);
                        int calDay = cal.get(Calendar.DAY_OF_YEAR);
                        int calYear = cal.get(Calendar.YEAR);
                        
                        return nowYear > calYear || (nowYear == calYear && nowDay > calDay);
                } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                }
        }
        
        public static boolean isAfterToday(String date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                        Calendar now = Calendar.getInstance();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(sdf.parse(date));
                        int nowDay = now.get(Calendar.DAY_OF_YEAR);
                        int nowYear = now.get(Calendar.YEAR);
                        int calDay = cal.get(Calendar.DAY_OF_YEAR);
                        int calYear = cal.get(Calendar.YEAR);
                        
                        return nowYear < calYear || (nowYear == calYear && nowDay < calDay);
                } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                }
        }
}
