package cli;

/*************Example***************
 CLASS: StringValidator.java
 CSC212 Data structures - Project phase II
 Fall 2023
 EDIT DATE:
 10/17/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Faris,    (ID443105725)
 ***********************************/
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
        //all have correct values
        int m = Integer.parseInt(c[0]);
        if (m < 1 || m > 12) {
            return false;
        }
        int d = Integer.parseInt(c[1]);
        if (d < 1 || d > 31) {
            return false;
        }
        int y = Integer.parseInt(c[2]);
        if (y < 1900 || y > 2100) {
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
        //all have correct values
        int h = Integer.parseInt(c[0]);
        if (h < 0 || h > 23) {
            return false;
        }
        int m = Integer.parseInt(c[1]);
        if (m < 0 || m > 59) {
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

    public static boolean isPhoneNumber(String s) {
        // isnt all numbers
        if (!isDigit(s)) {
            return false;
        }
        //isnt of length 10
        if (s.length() != 10) {
            return false;
        }
        return true;
    }
}
