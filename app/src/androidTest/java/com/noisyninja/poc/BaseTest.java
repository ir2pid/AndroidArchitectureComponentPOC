package com.noisyninja.poc;

/**
 * Created by sudiptadutta on 01/05/18.
 */

public class BaseTest {

    public static final int SHORT = 1000;
    public static final int MEDIUM = 5000;
    public static final int LONG = 20000;

    public void sleepShort() {
        try {
            Thread.sleep(SHORT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleepMedium() {
        try {
            Thread.sleep(MEDIUM);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleepLong() {
        try {
            Thread.sleep(LONG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
