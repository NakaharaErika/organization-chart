package service;

// DateUtil.java
public class DateUtil {
    public static String getYear(String dateString) {
        return (dateString != null && dateString.contains("-")) ? dateString.split("-")[0] : "";
    }

    public static String getMonth(String dateString) {
        return (dateString != null && dateString.contains("-")) ? dateString.split("-")[1] : "";
    }

    public static String getDay(String dateString) {
        return (dateString != null && dateString.contains("-")) ? dateString.split("-")[2] : "";
    }
}

