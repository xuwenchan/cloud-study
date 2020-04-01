package com.cloud.util;

public class Response<T> {

    private T result;
    private String code;
    private boolean success;


    public void setResult(T result){
        this.result=result;
    }

    public void setCode(String code){
        this.code=code;
    }

    public void setSuccess(boolean success){
        this.success=success;
    }

    public T getResult(){
        return this.result;
    }

    public String getCode(){
        return this.getCode();
    }

    public boolean isSuccess(){
        return this.success;
    }


}
