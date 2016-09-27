package org.cs.basic.ms;

/**
 * 使用ThreadLocal解决线程安全问题
 * @author Mr.Cheng
 *
 */
public class HandleDataSource {
	 public static final ThreadLocal<String> holder = new ThreadLocal<String>();  
	    public static void putDataSource(String datasource) {
	    	System.out.println("datasource:"+datasource);
	        holder.set(datasource);  
	    }  
	      
	    public static String getDataSource() {  
	        return holder.get();  
	    }      
}
