package org.cs.basic.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * jackson工具类
 * 通过JsonUtil.getInstance().方法名
 * @author Mr.Cheng
 *
 */
public class JsonUtil {
	private static JsonUtil ju;
	private static JsonFactory jf;
	private static ObjectMapper mapper;
	private JsonUtil(){}
	
	public static JsonUtil getInstance() {
		if(ju==null) ju = new JsonUtil();
		return ju;
	}
	
	public static ObjectMapper getMapper() {
		if(mapper==null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public static JsonFactory getFactory() {
		if(jf==null) jf = new JsonFactory();
		return jf;
	}
	/**
	 * 将对象转换成json 可以将list 转换成json
	 * @param obj
	 * @return
	 */
	public String obj2json(Object obj) {
		JsonGenerator jg = null;
		try {
			jf = getFactory();
			mapper = getMapper();
			StringWriter out = new StringWriter();
			jg = jf.createJsonGenerator(out);
			mapper.writeValue(jg, obj);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(jg!=null) jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 将json 对象转变成obj
	 * @param json
	 * @param clz
	 * @return
	 */
	public Object json2obj(String json,Class<?> clz) {
		try {
			mapper = getMapper();
			return mapper.readValue(json,clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	 /** 
     * 传入任意一个 object对象生成一个指定规格的字符串 
     *  
     * @param object 任意对象 
     * @return String 
     */  
    public static String objectToJson(Object object) {  
        StringBuilder json = new StringBuilder();  
        if (object == null) {  
            json.append("\"\"");  
        } else if (object instanceof String || object instanceof Integer || object instanceof Double || object instanceof Long) {  
            json.append("\"").append(object.toString()).append("\"");  
        } else {  
            json.append(beanToJson(object));  
        }  
        return json.toString();  
    }  

    /** 
     * 传入任意一个 Javabean对象生成一个指定规格的字符串 
     *  
     * @param bean bean对象 
     * @return String "{}" 
     */  
    public static String beanToJson(Object bean) {  
        StringBuilder json = new StringBuilder();  
        json.append("{");  
        PropertyDescriptor[] props = null;  
        try {  
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        if (props != null) {  
            for (int i = 0; i < props.length; i++) {  
                try {  
                    if(!(props[i].getPropertyType()).isInterface()){  
                        String name = objectToJson(props[i].getName());  
                        String value = objectToJson(props[i].getReadMethod().invoke(bean));  
                        json.append(name);  
                        json.append(":");  
                        json.append(value);  
                        json.append(",");  
                    }  
                } catch (Exception e) {  
                }  
            }  
            json.setCharAt(json.length() - 1, '}');  
        } else {  
            json.append("}");  
        }  
        return json.toString();  
    }  

    /** 
     * 通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串 
     *  
     * @param list 列表对象 
     * @return String "[{},{}]" 
     */  
    public static String listToJson(List<?> list) {  
        StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (list != null && list.size() > 0) {  
            for (Object obj : list) {  
                json.append(objectToJson(obj));  
                json.append(",");  
            }  
            json.setCharAt(json.length() - 1, ']');  
        } else {  
            json.append("]");  
        }  
        return json.toString();  
    }  
    /** 
     * 通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串 
     *  
     * @param set 列表对象 
     * @return String "[{},{}]" 
     */  

    public static String setTojson(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(objectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    /**
     * 对象数组转换为Json
     * @param array
     * @return
     */
    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(objectToJson(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * Map集合转换为Json
     * @param map
     * @return
     */
    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(objectToJson(key));
                json.append(":");
                json.append(objectToJson(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
    
   /* *//**
	 * json过滤器
	 * 过滤hibernate 联级属性
	 * @return
	 *//*
	public static JsonConfig getConfig(){
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
	        public boolean apply(Object arg0, String arg1, Object arg2) {
	             if (arg1.equals("Master") ||arg1.equals("reply")||arg1.equals("plate")) {
	                    return true;
	                } else {
	                    return false;
	                }
	        }
	    });
		return config;
	}
*/
}
