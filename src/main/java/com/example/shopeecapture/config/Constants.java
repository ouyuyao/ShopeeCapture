package com.example.shopeecapture.config;

//操作信息相关内容
public class Constants {
    public static int OPT_SUCCESS = 200; //request success and operate success
    public static int OPT_FAILED = 400; //request success and operate failed
    public static int NOT_RECORDS = 204; //request success and no records find
    public static int FAILED = 500; //request failed

    public static int RABBITMQ_TIMEOUT_SECONDS = 30;

    public static String NOT_RECORDS_CONTEXT = "NO_RECORDS";
    public static String SYSTEM_ERROR = "SYSTEM_ERROR";

    public static String STATUS_SUCCESS = "SUCCESS";
    public static String STATUS_FAILED = "FAILED";

    public static String INSERT_SUCCESS = "INSERT_SUCCESS";
    public static String INSERT_FAILED = "INSERT_FAILED";
    public static String UPDATE_SUCCESS = "UPDATE_SUCCESS";
    public static String UPDATE_FAILED = "UPDATE_FAILED";
    public static String DELETE_SUCCESS = "DELETE_SUCCESS";
    public static String DELETE_FAILED = "DELETE_FAILED";
    public static String QUERY_SUCCESS = "QUERY_SUCCESS";
    public static String QUERY_FAILED = "QUERY_FAILED";

    public static String DATE_FORMAT = "yyyy-MM-dd HH:ss:mm";

}
