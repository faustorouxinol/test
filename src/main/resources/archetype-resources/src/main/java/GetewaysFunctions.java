#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

 

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.drools.core.util.KieFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetewaysFunctions extends KieFunctions {
	private static final Logger logger = LoggerFactory.getLogger(GetewaysFunctions.class);	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean mapcontains(@SuppressWarnings("rawtypes") Map hm,String key,String valueTocompare ) {
		if(hm!=null && !hm.isEmpty()) {
		 Object value=hm.get(key);
	
		 if(value instanceof  String[]) {
			 logger.info("Value instance of array key:{}, value:{}, valueTocompare:{}",key,Arrays.toString((String[])value),valueTocompare);
			 
			return Arrays.stream((String[])value).anyMatch(valueTocompare::equals);
		 }else 
			 if(value instanceof List) {
			return	( (List)value).stream().anyMatch(valueTocompare::equals);
			 }
		}
		
		return false;
	}
	public static void main(String[] args)
    {
  
	/*	GetewaysFunctions.mapcontains(new HashMap() { {
	        put("02", new String[]{
	                "respond"
	            });
	            put("04", new String[]{
	                "return"
	            });
	        }
	    } ,"02","respond");*/

    }
}
