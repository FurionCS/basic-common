package org.cs.basic.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.apache.log4j.Logger;
/**
 * 使用hessian序列化
 * @author Mr.Cheng
 *
 */
public class HessionCodecFactory {

	  private final Logger logger = Logger.getLogger(HessionCodecFactory.class);  
	  /**
	   * 序列化
	   * @param obj
	   * @return
	   * @throws IOException
	   */
	    public byte[] serialize(Object obj) throws IOException {  
	        ByteArrayOutputStream baos = null;  
	        HessianOutput output = null;  
	        try {  
	            baos = new ByteArrayOutputStream(1024);  
	            output = new HessianOutput(baos);  
	            output.startCall();  
	            output.writeObject(obj);  
	            output.completeCall();  
	        } catch (final IOException ex) {  
	            throw ex;  
	        } finally {  
	            if (output != null) {  
	                try {  
	                    baos.close();  
	                } catch (final IOException ex) {  
	                    this.logger.error("Failed to close stream.", ex);  
	                }  
	            }  
	        }  
	        return baos != null ? baos.toByteArray() : null;  
	    }  
	  /**
	   * 反序列化
	   * @param in
	   * @return
	   * @throws IOException
	   */
	    public Object deSerialize(byte[] in) throws IOException {  
	        Object obj = null;  
	        ByteArrayInputStream bais = null;  
	        HessianInput input = null;  
	        try {  
	            bais = new ByteArrayInputStream(in);  
	            input = new HessianInput(bais);  
	            input.startReply();  
	            obj = input.readObject();  
	            input.completeReply();  
	        } catch (final IOException ex) {  
	            throw ex;  
	        } catch (final Throwable e) {  
	            this.logger.error("Failed to decode object.", e);  
	        } finally {  
	            if (input != null) {  
	                try {  
	                    bais.close();  
	                } catch (final IOException ex) {  
	                    this.logger.error("Failed to close stream.", ex);  
	                }  
	            }  
	        }  
	        return obj;  
	    }  

}
