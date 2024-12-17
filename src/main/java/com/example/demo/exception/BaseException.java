package com.example.demo.exception;

public class BaseException extends RuntimeException{

    private int code;

    private String message;

    private Object datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public BaseException(String message, int code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public BaseException(String message, int code, String message1, Object datas) {
        super(message);
        this.code = code;
        this.message = message1;
        this.datas = datas;
    }
}
