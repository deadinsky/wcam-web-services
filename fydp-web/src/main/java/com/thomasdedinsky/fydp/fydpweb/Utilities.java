package com.thomasdedinsky.fydp.fydpweb;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.models.Alert;
import com.thomasdedinsky.fydp.fydpweb.models.Phone;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {
    public static Map<String, Integer> activeSevereAlerts = new HashMap<>();
    public static boolean existsActiveSevereAlert = false;
    public static String alertText = "";
    public static List<String> phoneNumbers = new ArrayList<>();
    public static PhoneNumber fromNumber = new PhoneNumber(System.getenv("PHONE_NUMBER"));

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

    public static long macAddressToLong(String macAddress) {
        if (macAddress.length() != 17) {
            return -1;
        }
        String macAddressRaw = "" + macAddress.charAt(0) + macAddress.charAt(1)
                + macAddress.charAt(3) + macAddress.charAt(4)
                + macAddress.charAt(6) + macAddress.charAt(7)
                + macAddress.charAt(9) + macAddress.charAt(10)
                + macAddress.charAt(12) + macAddress.charAt(13)
                + macAddress.charAt(15) + macAddress.charAt(16);
        return Long.parseLong(macAddressRaw, 16);
    }

    public static void addModelAttributes(Model model, User user) {
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("isManager", user.isManager());
        model.addAttribute("isEnabled", user.isEnabled());
        model.addAttribute("isLoggedOut", false);
        model.addAttribute("existsActiveSevereAlert", existsActiveSevereAlert);
        model.addAttribute("alertText", alertText);
    }

    public static void refreshAlertText() {
        String newAlertText = "";
        for (Map.Entry<String, Integer> entry : activeSevereAlerts.entrySet()) {
            newAlertText += entry.getKey() + (entry.getValue() > 1 ? " x" + entry.getValue() : "") + " | ";
        }
        alertText = newAlertText.substring(0, newAlertText.length() - 3);
        existsActiveSevereAlert = !newAlertText.isEmpty();
    }

    public static void initializeAlerts(List<Alert> alerts) {
        for (Alert a : alerts) {
            if (a.isActive() && a.isSevere()) {
                String alertString = a.getAlertString();
                if (!activeSevereAlerts.containsKey(alertString)) {
                    activeSevereAlerts.put(alertString, 1);
                } else {
                    activeSevereAlerts.put(alertString, activeSevereAlerts.get(alertString) + 1);
                }
            }
        }
        refreshAlertText();
    }

    public static void initializePhones(List<Phone> phones) {
        for (Phone phone : phones) {
            phoneNumbers.add(phone.getId());
        }
        Twilio.init(System.getenv("ACCOUNT_SID"), System.getenv("AUTH_TOKEN"));
    }

    public static void addPhone(Phone phone) {
        if (!phoneNumbers.contains(phone.getId())) {
            phoneNumbers.add(phone.getId());
        }
    }

    public static void removePhone(Phone phone) {
        phoneNumbers.remove(phone.getId());
    }

    public static void sendAlertMessages(Alert alert) {
        String alertString = alert.getAlertString();
        for (String number : phoneNumbers) {
            Message.creator(new PhoneNumber(number), fromNumber, alertString).create();
        }
    }

    public static boolean refreshAlert(Alert alert) {
        if (!alert.isSevere()) {
            return false;
        }
        String alertString = alert.getAlertString();
        boolean doesAlertExist = activeSevereAlerts.containsKey(alertString);
        if (!doesAlertExist && !alert.isActive()) {
            return false;
        }
        if (!doesAlertExist) {
            activeSevereAlerts.put(alertString, 1);
            sendAlertMessages(alert);
            refreshAlertText();
            return true;
        }
        Integer alertCount = activeSevereAlerts.get(alertString);
        if (alert.isActive()) {
            activeSevereAlerts.put(alertString, alertCount + 1);
            refreshAlertText();
            return true;
        }
        if (alertCount == 1) {
            activeSevereAlerts.remove(alertString);
            refreshAlertText();
            return true;
        }
        activeSevereAlerts.put(alertString, alertCount - 1);
        refreshAlertText();
        return true;
    }
}
