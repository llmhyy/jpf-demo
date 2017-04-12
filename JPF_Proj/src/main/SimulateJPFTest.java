

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SimulateJPFTest {
	public static void main(String[] args) {
		String targetClass = "PushPop";//"org.apache.commons.math.stat.descriptive.DescriptiveStatistics";
		String method = "PushPop.push_pop(sym#sym#sym)";//"org.apache.commons.math.stat.descriptive.DescriptiveStatistics.addValue(sym)";
		String classPath = "bin";
		String[] appArgs = {
				"+classpath="+classPath,
				"+target="+targetClass,
				"+symbolic.method="+method,
				"+vm.storage.class=nil",
				"+search.multiple_errors=false",
				"+symbolic.debug=true",
				"+symbolic.min_int=-200",
				"+symbolic.max_int=200",
				"+symbolic.min_double=-2000.0",
				"+symbolic.max_double=2000.0",
				"+symbolic.debug=on",
				"+search.class=.search.heuristic.BFSHeuristic",
				"+listener=gov.nasa.jpf.symbc.sequences.SymbolicSequenceListener"
				};
//		new Test().runJPF(new String[]{"+app=src/main/addValue.jpf"});
		SimulateJPF.runJPF(appArgs);
		System.out.println("======================================================================");
//		testCoverage();
	}

	private static void testCoverage() {

		String targetClass = "org.apache.commons.math.stat.descriptive.DescriptiveStatistics";
		String method = "org.apache.commons.math.stat.descriptive.DescriptiveStatistics.addValue(sym)";
		String classPath = "bin";
		String[] appArgs = {
				"+classpath="+classPath,
				"+target="+targetClass,
				"+symbolic.method="+method,
				"+vm.storage.class=nil",
				"+search.multiple_errors=false",
				"+symbolic.debug=true",
				"+symbolic.min_int=-200",
				"+symbolic.max_int=200",
				"+symbolic.min_double=-2000.0",
				"+symbolic.max_double=2000.0",
				"+search.class=.search.heuristic.BFSHeuristic",
				"+listener=gov.nasa.jpf.symbc.sequences.SymbolicSequenceListener,.listener.CoverageAnalyzer",
				"+coverage.include=T1,T2",
				"+coverage.show_methods=true"
				};
		SimulateJPF.runJPF(appArgs);
		
	}

	
	
	static void reflect(String jpf)throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, IOException{
		  Class clazz = Class.forName("gov.nasa.jpf.tool.RunJPF");  
	      Method method = clazz.getMethod("main", String[].class);//µ÷Main·½·¨  
	      method.invoke(null, new Object[]{new String[]{jpf}});
	}
}
