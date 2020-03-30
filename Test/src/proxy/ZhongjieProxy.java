package proxy;

/**
 * 代理类
 * @author 徐文产
 *
 */
public class ZhongjieProxy implements Jiaoyi1{

	public FangNu fangNu;
	
	public FangNu getFangNu() {
		return fangNu;
	}
	public void setFangNu(FangNu fangNu) {
		this.fangNu = fangNu;
	}
	public ZhongjieProxy(FangNu fangNu) {
		this.fangNu=fangNu;
	}
	@Override
	public void maifang(String houseName, double money) {
		System.out.println("中介开始筛选房子.............");
		System.out.println("中介开始将房源推荐给用户.......");
		System.out.println("用户看上该房子，并产生交易");
		this.fangNu.maifang(houseName, money);
	}
}
