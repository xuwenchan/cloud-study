package NetNioTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class UDPClient {

	public static void main(String[] arsg) {
		client();
	}
	
	public static void client() {
		try {
			//创建一个客户端并设置连接的地址和端口
			SocketChannel clientChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
			//切换非阻塞模式
			clientChannel.configureBlocking(false);
			//为缓冲区分配空间
			ByteBuffer buf=ByteBuffer.allocate(1024);
			//向缓冲区中存取数据
			buf.put("i love you".getBytes());
			//切换模式
			buf.flip();
			clientChannel.write(buf);
			//关闭客户端
			clientChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
