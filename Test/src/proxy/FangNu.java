package proxy;
/**
 * 买房者
 * 
 *
 */
public class FangNu implements Jiaoyi1{

	@Override
	public void maifang(String houseName, double money) {
		System.out.println("买房者购买的房子名称为："+houseName+",购买房子所支付的金额为："+money);
	}
}
