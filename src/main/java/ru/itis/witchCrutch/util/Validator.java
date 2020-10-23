package ru.itis.witchCrutch.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_PATTER = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    private static final String NAME_PATTERN = "^[A-Za-zА-ЯЁа-яё]+$";

    public static boolean validAuth(String email, String password) {
        boolean result = false;
        if (email != null && password != null) {
            result = valid(email, EMAIL_PATTERN) && valid(password, PASSWORD_PATTER);
        }
        return result;
    }

    public static boolean validRegister(String name, String lastname, String email, String password, String password_again) {
        boolean result = false;
        if (lastname != null && name != null && email != null && password != null && password.equals(password_again)) {
            boolean emailPasswordValid = valid(email, EMAIL_PATTERN) && valid(password, PASSWORD_PATTER);
            boolean nameLastnameValid = valid(name, NAME_PATTERN) && valid(lastname, NAME_PATTERN);
            result = emailPasswordValid && nameLastnameValid;
        }
        return result;
    }

    private static boolean valid(String expression, String pattern) {
        Pattern emailPattern = Pattern.compile(pattern);
        Matcher emailMatcher = emailPattern.matcher(expression);
        return emailMatcher.matches();
    }
}
