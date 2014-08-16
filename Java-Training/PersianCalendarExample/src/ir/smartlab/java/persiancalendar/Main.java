package ir.smartlab.java.persiancalendar;

import java.util.Date;
import java.util.GregorianCalendar;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.Calendar;

public class Main {

    public static void main(String[] args) {
        // Ex. 1
        // Create a PersianCalendar object with current date and time of system
        System.out.println("Create a PersianCalendar object with current date and time of system:");
        PersianCalendar persianCalendar1 = new PersianCalendar(new Date());

        System.out.println("Year:" + persianCalendar1.get(Calendar.YEAR));
        System.out.println("Month:" + (persianCalendar1.get(Calendar.MONTH) + 1)); // 0-11
        System.out.println("Day:" + persianCalendar1.get(Calendar.DAY_OF_MONTH));
        System.out.println("Hour:" + persianCalendar1.get(Calendar.HOUR_OF_DAY)); // HOUR_OF_DAY:
                                                                     // 0-23,
                                                                     // HOUR:0-11
        System.out.println("Minute:" + persianCalendar1.get(Calendar.MINUTE));
        System.out.println("Second:" + persianCalendar1.get(Calendar.SECOND));

        // Ex. 2
        // Convert Persian to Gregorian Calendar
        System.out.println("\nConvert Persian to Gregorian Calendar:");
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(persianCalendar1.getTime());

        System.out.println("Year:" + gregorianCalendar.get(Calendar.YEAR));
        System.out.println("Month:" + (gregorianCalendar.get(Calendar.MONTH) + 1));
        System.out.println("Day:" + gregorianCalendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("Hour:" + gregorianCalendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Minute:" + gregorianCalendar.get(Calendar.MINUTE));
        System.out.println("Second:" + gregorianCalendar.get(Calendar.SECOND));

        // Ex. 3
        // Convert Gregorian Calendar to Persian (Jalali) Calendar
        System.out.println("\nConvert Gregorian Calendar to Persian (Jalali) Calendar:");
        PersianCalendar persianCalendar3 = new PersianCalendar();
        persianCalendar3.setTime(gregorianCalendar.getTime());

        System.out.println("Year:" + persianCalendar3.get(Calendar.YEAR));
        System.out.println("Month:" + (persianCalendar3.get(Calendar.MONTH) + 1));
        System.out.println("Day:" + persianCalendar3.get(Calendar.DAY_OF_MONTH));
        System.out.println("Hour:" + persianCalendar3.get(Calendar.HOUR_OF_DAY));
        System.out.println("Minute:" + persianCalendar3.get(Calendar.MINUTE));
        System.out.println("Second:" + persianCalendar3.get(Calendar.SECOND));

    }

}
