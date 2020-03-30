import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {

    public static void main(String[] args){
        URL url= null;
        HttpURLConnection httpURLConnection=null;
        BufferedInputStream buf=null;
        BufferedOutputStream buo=null;
        try {
            url = new URL("https://erlang.org/download/otp_src_22.1.tar.gz");
            httpURLConnection=(HttpURLConnection)url.openConnection();
            buf=new BufferedInputStream(httpURLConnection.getInputStream());
            File file=new File("D://download//otp_src_22.1.tar.gz");
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            buo=new BufferedOutputStream(new FileOutputStream(file));
            byte[] b=new byte[1024];
            while (buf.read(b)!=-1){
                buo.write(b);
                buo.flush();
            }
            System.out.println("下载完");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(buf!=null){
                    buf.close();
                }
                if(buo!=null){
                    buo.close();
                }
            }catch(Exception e){

            }

        }

        ;

    }
}
