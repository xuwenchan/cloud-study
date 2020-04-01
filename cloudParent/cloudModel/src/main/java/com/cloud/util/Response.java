package com.cloud.util;

public class Response<T> extends ReponseResult {
    private T result;

    public void setResult(T result){
        this.result=result;
    }

    public T getResult(){
        return this.result;
    }


}
