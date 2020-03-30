package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionTest {

	
	public static void main(String[] args) {
		try {
			URL url=new URL("https://blog.csdn.net/weixin_42548604/article/details/100184152");
			HttpURLConnection httpUrlConnection=  (HttpURLConnection) url.openConnection();
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(),"UTF-8"));
			String content=null;
			while((content=bufferedReader.readLine())!=null) {
				System.out.println(content);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
