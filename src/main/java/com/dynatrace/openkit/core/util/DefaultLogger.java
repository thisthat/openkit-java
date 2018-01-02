package com.dynatrace.openkit.core.util;

import com.dynatrace.openkit.api.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DefaultLogger implements Logger {

    private boolean verbose;

    static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static String getUTCTime() {
        return dateFormat.format(new Date());
    }

    public DefaultLogger(boolean verbose) {
        this.verbose = verbose;
    }

    @Override
    public void error(String message) {
        System.out.println(getUTCTime() + " [ERROR] " + message);
    }

    @Override
    public void error(String message, Throwable t) {
        System.out.println(getUTCTime() + " [ERROR] " + message);
        t.printStackTrace(System.out);
    }

    @Override
    public void warning(String message) {
        System.out.println(getUTCTime() + " [WARN ] " + message);
    }

    @Override
    public void info(String message) {
        if(isInfoEnabled()) {
            System.out.println(getUTCTime() + " [INFO ] " + message);
        }
    }

    @Override
    public void debug(String message) {
        if(isDebugEnabled()) {
            System.out.println(getUTCTime() + " [DEBUG] " + message);
        }
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public boolean isInfoEnabled() {
        return verbose;
    }

    public boolean isDebugEnabled() {
       return verbose;
    }


}
