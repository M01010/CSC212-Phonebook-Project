package cli;

public class StringValidator {


    public static boolean isDate(String s) {
        String[] c = s.split("/");
        // string formatted as x/y/z
        if (c.length != 3) {
            return false;
        } // all have correct lengths
        else if (c[0].length() != 2 || c[1].length() != 2 || c[2].length() != 4) {
            return false;
        }
        // all are digits
        if ((!isDigit(c[0]) || !isDigit(c[1])) || !isDigit(c[2])) {
            return false;
        }
        return true;
    }

    public static boolean isTime(String s) {
        String[] c = s.split(":");
        // string formatted as x:y
        if (c.length != 2) {
            return false;
        } // all have correct lengths
        else if (c[0].length() != 2 || c[1].length() != 2) {
            return false;
        }
        // both are digits
        if (!isDigit(c[0]) || !isDigit(c[1])) {
            return false;
        }
        return true;
    }

    public static boolean isDateTime(String s) {
        String[] c = s.split(" ");
        // string formatted as x y
        if (c.length != 2) {
            return false;
        } // first is date and second is time
        if (!isDate(c[0]) || !isTime(c[1])) {
            return false;
        }
        return true;
    }

    public static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isEmail(String s) {
        // has no @
        if (s.indexOf('@') == -1) {
            return false;
        }
        // has no dot
        if (s.indexOf('.') == -1) {
            return false;
        }
        return true;
    }
}
