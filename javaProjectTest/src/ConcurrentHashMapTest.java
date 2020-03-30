import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        System.out.println("-----------------");
        ConcurrentHashMap<String,Integer> concurrentHashMap=new ConcurrentHashMap<String,Integer>();
        concurrentHashMap.put("1",1);
        add(1,2);
    }

    public static int add(int a,int b){
        return a+b;
    }
}
