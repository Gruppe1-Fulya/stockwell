package org.atom;

import java.text.DecimalFormat;

public class Util {

    public static String CoolNumber(int number) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(number);
    }
}
