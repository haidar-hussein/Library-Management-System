package project02;

import java.time.LocalDate;

public class Date {


    private final int year;
    private final int month;
    private final int day;

    // constructor
    public Date(int year, int month, int day) {

        this.year = year;
        this.month = month;
        this.day = day;

    }

    // public getters (using them on some other classes)

    public static int getNowYear() {
        return LocalDate.now().getYear();
    }


    //public helper
    public int calcAge() {


        // this is to get the updated time (since once local date is created it's frozen)
        // and having today is for making the date changeable betweeen now year , now month , and now day
        LocalDate today = LocalDate.now();

        int now_year = today.getYear();
        int now_month = today.getMonthValue() ;
        int now_day = today.getDayOfMonth();


        int age = 0;

        if (now_month > month) {
            age = now_year - year;

        } else if (now_month < month) {
            age = now_year - year - 1;

        } else  {
            if (now_day >= day) {
                age = now_year - year;

            } else  {
                age = now_year - year - 1;
            }
        }

        return age;
    }

    @Override
    public String toString() {
        return  year +
                "/" + month +
                "/" + day;
    }
}
