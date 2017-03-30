package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TestURL {
	public static void main(String[] args) {
		try {
//			test("http://lavasoft.blog.51cto.com/62575/120430");
//			test("file:/E:/workspace/JPF/jpf_src/jpf-core/build/1.txt");
//			new TestURL().test("file://E:\\workspace\\JPF\\jpf_src\\jpf-core//build//jpf.jar");
			new TestURL().test("file:\\E:\\workspace\\JPF\\jpf_src\\jpf-core//build//1.txt");	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test(String site) throws IOException {     
		URL url = new URL(site);        
        //打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。        
        InputStream in = url.openStream();        
        int c;        
        while ((c = in.read()) != -1)        
                System.out.print(c);        
        in.close(); 
    }   
}
