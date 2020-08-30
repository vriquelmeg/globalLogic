package com.vriquelme.demo.model.util;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    /**
     * Validate Email
     * @param p_email
     * @return
     */
    public static boolean getValidEmail(String p_email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(p_email);
        return matcher.matches();
    }

    public static boolean validFormatPassword(String password){
        Pattern pattern = Pattern.compile("(^[A-Z][a-z]+(\\d){2}$)|(^[a-z]+[A-Z](\\d){2}$)|(^(\\d){2}[A-Z]{1}[a-z]+$)|(^[a-z]+(\\d){2}[A-Z])");
        Matcher matcher = pattern.matcher(password);
        return  matcher.matches();
    }

    /**
     * Validate that the parameter is not empty: that is: null, or empty String, or
     * a String whose trim () is empty, or an empty Collection, or an empty Map
     * @param p_param
     * @return
     */
    public static boolean isEmpty(Object p_param) {

        if ( p_param == null ) {
            return true;
        }
        if ( p_param instanceof String ) {
            String str = (String) p_param;
            if ( str.trim().length() == 0 ) {
                return true;
            }
        }
        if ( p_param instanceof Collection ) {
            Collection params = ( Collection ) p_param;
            return params.isEmpty();
        }
        if ( p_param instanceof Map) {
            Map params = ( Map ) p_param;
            return params.isEmpty();
        }
        return false;
    }

    /**
     * Valida que el parametro sea nulo.
     * @param p_param
     * @return
     */
    public static boolean isNull(Object p_param) {

        if ( p_param == null ) {
            return true;
        }
        return false;
    }

    public static UUID generateId() {
        return UUID.randomUUID();
    }
}
