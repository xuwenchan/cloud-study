package proxy;

/**
 * ������
 * @author ���Ĳ�
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
		System.out.println("�н鿪ʼɸѡ����.............");
		System.out.println("�н鿪ʼ����Դ�Ƽ����û�.......");
		System.out.println("�û����ϸ÷��ӣ�����������");
		this.fangNu.maifang(houseName, money);
	}
}
