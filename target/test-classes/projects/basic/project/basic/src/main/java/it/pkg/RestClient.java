package it.pkg;
 
import org.springframework.web.client.RestTemplate;
 

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
public class RestClient  {
	//protected RestTemplates restTemplate = new RestTemplates();
	RestTemplate rest;
	StopWatch watch = new StopWatch();
	private static final Logger _log = LoggerFactory.getLogger(RestClient.class);	
public RestClient() {
	rest= new RestTemplate();
}

public  <T> T  get(String url, Class<T> responseType ) 
{
	try {
		watch.start();
		return rest.getForObject(url, responseType);
	} finally {
		watch.stop();
		_log.info("RestTemplates.get took:{}" , watch);
	}
}

//POST
public  <T> T  post(String url,  @Nullable Object request, Class<T> responseType ) throws Exception 
{ 

try {
	return rest.postForObject(new URI(url),request, responseType); //new URI();
}finally{
	
}
}

public  <T> T  put(String url,  @Nullable Object request, Class<T> responseType ) throws Exception 
{
	return rest.postForObject(new URI(url),request, responseType); //new URI();
}

public void  delete(String url, Object... uriVariables) throws Exception 
{
	 rest.delete(url,uriVariables); //new URI();
}



}
