package NetNioTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class UDPServer {

	public static void main(String[] args) {
		server();
	}
	
	public static void server() {
		try {
			//创建一个服务器
			ServerSocketChannel serverChannel=ServerSocketChannel.open();
			//设置为非阻塞模式
			serverChannel.configureBlocking(false);
			//设置绑定的端口号
			serverChannel.bind(new InetSocketAddress(9898));
			//创建一个选择器
			Selector selector =Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			while(selector.select()>0) {
				Iterator<SelectionKey> it=selector.selectedKeys().iterator();
				while(it.hasNext()) {
					//获取准备就绪的事件
					SelectionKey sk=it.next();
					if(sk.isAcceptable()) {//测试此密钥的通道是否已准备好接受新的套接字连接
						SocketChannel sChannel=serverChannel.accept();
						//设置为非阻塞模式
						sChannel.configureBlocking(false);
						//将通道注册到选择器上
						sChannel.register(selector, SelectionKey.OP_READ);
					}else if(sk.isReadable()) {//测试此密钥的频道是否可以阅读。 
						SocketChannel sChannel=(SocketChannel)sk.channel();
						//创建缓冲区读取数据
						ByteBuffer buf=ByteBuffer.allocate(1024);
						int len=0;
						while((len=sChannel.read(buf))>0) {
							buf.flip();
							System.out.println(new String(buf.array(),0,len));
							buf.clear();
						}
					}
					it.remove();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startServer() {
		try {
			ServerSocketChannel ssChannel=ServerSocketChannel.open();
			//切换到非阻塞模式
			ssChannel.configureBlocking(false);
			ssChannel.bind(new InetSocketAddress(9898));
			//获取选择器
			Selector selector=Selector.open();
			//将通道注册到选择器上，并且指定"监听接收事件"
			ssChannel.register(selector,SelectionKey.OP_ACCEPT);
			//轮询式的获取选择器上已经“准备就绪”的事件
			while(selector.select()>0) {
				Iterator<SelectionKey> it=  selector.selectedKeys().iterator();
				while(it.hasNext()) {
					//获取准备就绪的事件
					SelectionKey sk=it.next();
					//判断具体是什么事件的准备就绪
					if(sk.isAcceptable()) {
						SocketChannel sChannel=ssChannel.accept();
						//切换到非阻塞式模式
						sChannel.configureBlocking(false);
						//将通道注册到选择器上
						sChannel.register(selector, SelectionKey.OP_READ);
					}else if(sk.isReadable()) {
						SocketChannel sChannel=(SocketChannel) sk.channel();
						//读取数据
						ByteBuffer buf=ByteBuffer.allocate(1024);
						int len=0;
						while((len=sChannel.read(buf))>0) {
							buf.flip();
							System.out.println(new String(buf.array(),0,len));
							buf.clear();
						}
					}
					it.remove();
					
				}
			}
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
