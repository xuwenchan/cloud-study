package client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClient {

    private int timeout;
    private int poolSize;
    private boolean insecure;

    private CloseableHttpClient httpClient;// HttpClient的客户端

    // 响应模型
    CloseableHttpResponse response;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public boolean isInsecure() {
        return insecure;
    }

    public void setInsecure(boolean insecure) {
        this.insecure = insecure;
    }

    public void init(){
        this.timeout=5000;
        this.poolSize=10;
        this.insecure=false;
        this.httpClient= HttpClientBuilder.create().build();
    }

    public HttpClient(int timeout,int poolSize){
        this.timeout=timeout;
        this.poolSize=poolSize;
    }



    public String executePostMethod(String url, String query, Header[] headers,int timeout){
        return "";
    }

    public String executePostMethod(String url, String query, Header[] headers){
        return "";
    }

    public String executeGetMethod(String url, String query,Header[] headers){
        HttpGet httpGet=new HttpGet(url);
        httpGet.setHeaders(headers);
        return "";
    }

    public String executeDeleteMethod(String url){
        return "";
    }


    public void close(){
        try{
            if(this.httpClient!=null){
                this.httpClient.close();
            }
            if(this.response!=null){
                this.response.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }


}
