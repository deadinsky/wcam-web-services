package com.thomasdedinsky.fydp.fydpweb;

public class Utilities {
    public static String longToMacAddress(long id) {
        String macAddressRaw = Long.toHexString(id);
        //Only a few cases, might as well do it using a switch statement
        switch (macAddressRaw.length()) {
            case 0:
                return "00:00:00:00:00:00";
            case 1:
                return "00:00:00:00:00:0" + macAddressRaw;
            case 2:
                return "00:00:00:00:00:" + macAddressRaw;
            case 3:
                return "00:00:00:00:0" + macAddressRaw.charAt(0) + ":"
                        + macAddressRaw.charAt(1) + macAddressRaw.charAt(2);
            case 4:
                return "00:00:00:00:" + macAddressRaw.charAt(0) + macAddressRaw.charAt(1) + ":"
                        + macAddressRaw.charAt(2) + macAddressRaw.charAt(3);
            case 5:
                return "00:00:00:0" + macAddressRaw.charAt(0) + ":"
                        + macAddressRaw.charAt(1) + macAddressRaw.charAt(2) + ":"
                        + macAddressRaw.charAt(3) + macAddressRaw.charAt(4);
            case 6:
                return "00:00:00:" + macAddressRaw.charAt(0) + macAddressRaw.charAt(1) + ":"
                        + macAddressRaw.charAt(2) + macAddressRaw.charAt(3) + ":"
                        + macAddressRaw.charAt(4) + macAddressRaw.charAt(5);
            case 7:
                return "00:00:0" + macAddressRaw.charAt(0) + ":"
                        + macAddressRaw.charAt(1) + macAddressRaw.charAt(2) + ":"
                        + macAddressRaw.charAt(3) + macAddressRaw.charAt(4) + ":"
                        + macAddressRaw.charAt(5) + macAddressRaw.charAt(6);
            case 8:
                return "00:00:" + macAddressRaw.charAt(0) + macAddressRaw.charAt(1) + ":"
                        + macAddressRaw.charAt(2) + macAddressRaw.charAt(3) + ":"
                        + macAddressRaw.charAt(4) + macAddressRaw.charAt(5) + ":"
                        + macAddressRaw.charAt(6) + macAddressRaw.charAt(7);
            case 9:
                return "00:0" + macAddressRaw.charAt(0) + ":"
                        + macAddressRaw.charAt(1) + macAddressRaw.charAt(2) + ":"
                        + macAddressRaw.charAt(3) + macAddressRaw.charAt(4) + ":"
                        + macAddressRaw.charAt(5) + macAddressRaw.charAt(6) + ":"
                        + macAddressRaw.charAt(7) + macAddressRaw.charAt(8);
            case 10:
                return "00:" + macAddressRaw.charAt(0) + macAddressRaw.charAt(1) + ":"
                        + macAddressRaw.charAt(2) + macAddressRaw.charAt(3) + ":"
                        + macAddressRaw.charAt(4) + macAddressRaw.charAt(5) + ":"
                        + macAddressRaw.charAt(6) + macAddressRaw.charAt(7) + ":"
                        + macAddressRaw.charAt(8) + macAddressRaw.charAt(9);
            case 11:
                return "0" + macAddressRaw.charAt(0) + ":"
                        + macAddressRaw.charAt(1) + macAddressRaw.charAt(2) + ":"
                        + macAddressRaw.charAt(3) + macAddressRaw.charAt(4) + ":"
                        + macAddressRaw.charAt(5) + macAddressRaw.charAt(6) + ":"
                        + macAddressRaw.charAt(7) + macAddressRaw.charAt(8) + ":"
                        + macAddressRaw.charAt(9) + macAddressRaw.charAt(10);
            case 12:
                return macAddressRaw.charAt(0) + macAddressRaw.charAt(1) + ":"
                        + macAddressRaw.charAt(2) + macAddressRaw.charAt(3) + ":"
                        + macAddressRaw.charAt(4) + macAddressRaw.charAt(5) + ":"
                        + macAddressRaw.charAt(6) + macAddressRaw.charAt(7) + ":"
                        + macAddressRaw.charAt(8) + macAddressRaw.charAt(9) + ":"
                        + macAddressRaw.charAt(10) + macAddressRaw.charAt(11);
            default:
                return Long.toString(id);
        }
    }
}
