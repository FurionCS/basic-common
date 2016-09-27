package org.cs.basic.ms;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

//@Aspect
//@Component
public class DataSourceAspect {
	//@Pointcut("execution(* org.cs.cms.dao.*.*(..))")    
    public void pointCut(){};    
      
   // @Before(value = "pointCut()")  
     public void before(JoinPoint point)  
        {  
            Object target = point.getTarget();  
        //    System.out.println(target.toString());  
            String method = point.getSignature().getName();  
        //    System.out.println(method);  
            Class<?>[] classz = target.getClass().getInterfaces();  
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();  
            try {  
                Method m = classz[0].getMethod(method, parameterTypes);  
        //        System.out.println(m.getName());  
                System.out.println(m.isAnnotationPresent(DataSource.class)+"//"+m.getAnnotations().toString());
                if (m != null && m.isAnnotationPresent(DataSource.class)) {  
                    DataSource data = m.getAnnotation(DataSource.class);  
                    HandleDataSource.putDataSource(data.value());  
                }  
                  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
}
