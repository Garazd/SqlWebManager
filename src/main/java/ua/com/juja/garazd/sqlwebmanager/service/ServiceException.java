package ua.com.juja.garazd.sqlwebmanager.service;

public class ServiceException extends Exception {
    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}