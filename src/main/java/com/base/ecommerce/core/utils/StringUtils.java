package com.base.ecommerce.core.utils;

public class StringUtils {

    public static final String EMPTY_TEXT = "";
    public static final String SPACE_EMPTY_TEXT = " ";
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    StringBuilder builder;


    private StringUtils() {
        throw new UnsupportedOperationException("util class");
    }

    public static boolean isEmpty(String value) {
        return value.length() == 0 && value.trim().isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return !(value.length() == 0) && !value.trim().isEmpty();
    }

    public StringUtils addValue(String value) {
        builder = new StringBuilder();
        if (value != null ) {
            builder.append(value);
        }
        return this;
    }

    public String getValue(){
        return builder.toString();
    }


    public static boolean isRoman(String value) {
        return value.matches("^[IVXLCDM]+$");
    }

    public static boolean isArabic(String value) {
        return value.matches("^[0-9]+$");
    }
    public static boolean isNumber(String value) {
        return value.matches("^[0-9]+$");
    }
    public static boolean isLetter(String value) {
        return value.matches("^[a-zA-Z]+$");
    }
    public static boolean isLetterOrNumber(String value) {
        return value.matches("^[a-zA-Z0-9]+$");
    }
    public static boolean isLetterOrNumberOrSpace(String value) {
        return value.matches("^[a-zA-Z0-9 ]+$");
    }
    public static String getString(Object value) {
        return value == null ? EMPTY_TEXT : value.toString();
    }
}
