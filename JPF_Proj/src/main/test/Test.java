package test;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.JPFListener;
import gov.nasa.jpf.listener.EndlessLoopDetector;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, IOException {
		reflect("src\\main\\addValue.jpf");
		new Test().runJPF(new String[]{"src\\main\\example\\Racer.jpf"});
//	      ArrayList<String> commandList = new ArrayList<String>();
//	      commandList.add("java");
//	      commandList.add("-jar");
//	      commandList.add("E:\\workspace\\JPF\\jpf-demo\\JPF_Proj\\libs\\RunJPF.jar");
//	      commandList.add("+shell.port=4242");
//	      commandList.add("E:\\workspace\\JPF\\jpf-demo\\JPF_Proj\\src\\shuffle.jpf");
//	      String[] cmdArgs = commandList.toArray(new String[commandList.size()]);
//	      String workingDir = "E:\\workspace\\JPF\\jpf-demo\\working";
//	      // we inherit the parent proc environment
//	      Process jpf = Runtime.getRuntime().exec(cmdArgs, null, new File(workingDir));
//
//	       PrintWriter outputStream = new PrintWriter(new File("E:\\workspace\\JPF\\jpf-demo\\out"));
//	       if (outputStream != null){
//	         StringBuffer buff = new StringBuffer();
//	         for (String string : commandList) {
//	           buff.append(string).append(" ");
//	         }
//	         System.out.println("Executing command: " + buff.toString());
//	         new IORedirector(jpf.getInputStream(), outputStream).start();
//	         new IORedirector(jpf.getErrorStream(), outputStream).start();
//	       }
//	       
//	      //Runs until the jpf process terminates, then 
//	      //sets isJPFRunning to false
//	      new Thread(){
//	        @Override
//	        public void run(){
//	          try {
//	            jpf.waitFor();
//	          } catch (InterruptedException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	          }
//	        }
//	      }.start();
	}

	void runJPF (String[] args) {

		   // [optionally] if you pass through command line args, 
		   // 'null' any consumed args not to be JPF-processed
//		   listener.filterArgs( args);
		   Config config = JPF.createConfig( args);
		   // set special config key/value pairs here..

		JPFListener listener = new EndlessLoopDetector(config);
		JPF jpf = new JPF( config);
		   jpf.addListener( listener);
		   jpf.run();
		}
	
	static void reflect(String jpf)throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, IOException{
		  Class clazz = Class.forName("gov.nasa.jpf.tool.RunJPF");  
	      Method method = clazz.getMethod("main", String[].class);//µ÷Mian·½·¨  
	      method.invoke(null, new Object[]{new String[]{jpf}});
	}
}

class IORedirector extends Thread {
	 
	   private PrintWriter out;
	   private BufferedReader in;
	 
	   public IORedirector(final InputStream in, final PrintWriter out) {
	     this.in = new BufferedReader(new InputStreamReader(in));
	     this.out = out;
	   }
	 
	   @Override
	   public void run() {
	     try {
	       String s;
	       while ((s = in.readLine()) != null) {
	         out.println(s);
	       }
	     } catch (IOException ex) {
	       ex.printStackTrace();
	     }
	   }
	 }
