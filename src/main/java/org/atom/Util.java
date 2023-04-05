package org.atom;

public class Util {

    public static String CoolNumber(int number) {
        String coolNumber = "";
        int i = 0;
        while (number % 10 > 1) {
            coolNumber += Integer.toString(number % 10);
            if (i >= 3) {
                coolNumber += ",";
                i = -1;
            }
            number = number % 10;
            i++;
        }
        return coolNumber;
    }
}
