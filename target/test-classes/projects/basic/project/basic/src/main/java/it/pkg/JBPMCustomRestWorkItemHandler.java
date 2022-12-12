package it.pkg;

import java.util.Map;

import org.jbpm.bpmn2.handler.WorkItemHandlerRuntimeException;
/**
 * @author Fausto Rouxinol 
 * 
 */
import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessWorkItemHandlerException;
import org.kie.api.runtime.process.ProcessWorkItemHandlerException.HandlingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JBPMCustomRestWorkItemHandler extends AbstractLogOrThrowWorkItemHandler {
	enum WSMode {
		SYNC, ASYNC, ONEWAY;
	}

	String cmisUrl;
	String user;
	String password;
	KieSession ksession;
	String Url;
	
	
    private String processId="SDCCS.HandlerException";
 
    private int retries=2;
    
	
	private static Logger logger = LoggerFactory.getLogger(JBPMCustomRestWorkItemHandler.class);

	
	public JBPMCustomRestWorkItemHandler(String Url, String user, String password) {
		{
			if (this.password == null || this.password.isEmpty()) {
				this.password = System.getProperty("SERV_URL_PASSWORD");
				logger.debug("find system property {}:*******", "PASSWORD");
			}
			if (this.user == null || this.user.isEmpty()) {
				this.user = System.getProperty("SERV_URL_USER");
				logger.debug("find system property {}:{}", "USER", this.user);
			}
			if (this.cmisUrl == null || this.cmisUrl.isEmpty()) {
				this.cmisUrl = System.getProperty("SERV_URL");
				logger.debug("find system property {}:{}", "URL", this.cmisUrl);
			}
			//
			if (this.password == null || this.password.isEmpty()) {
				this.password = System.getenv("SERV_PASSWORD");
				logger.debug("find environment variables  {}:*********", "SERV_URL_PASSWORD");
			}
			if (this.user == null || this.user.isEmpty()) {
				this.user = System.getenv("SERV_URL_USER");
				logger.debug("find environment variables  {}:{}", "SERV_URL_USER", this.user);
			}
			if (this.Url == null || this.cmisUrl.isEmpty()) {
				this.Url = System.getenv("SERV_URL");
				logger.debug("find environment variables  {}:{}", "SERV_URL", this.Url);
			}
			
			
			if (this.password == null || this.password.isEmpty()) {
				this.password = password;
				logger.debug("get from config {}:*********", "password");
			}
			if (this.user == null || this.user.isEmpty()) {
				this.user = user;
				logger.debug("get from config   {}:{}", "user", this.user);
			}
			if (this.Url == null || this.Url.isEmpty()) {
				this.Url = cmisUrl;
				logger.debug("get from config   {}:{}", "Url", this.Url);
			}
			

			if (this.password == null || this.password.isEmpty()) {
				logger.error("missing password information !!! ");
			}
			if (this.user == null || this.user.isEmpty()) {
				logger.error("missing user information !!! ");
			}
			if (this.cmisUrl == null || this.cmisUrl.isEmpty()) {
				logger.error("missing service url   !!! ");
			}
		}

	}
	

	protected String nonNull(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}
	
	
	protected void handleException(Throwable cause, Map<String, Object> handlerInfoMap) {
		if (handlingProcessId != null && handlingStrategy != null) {
			throw new ProcessWorkItemHandlerException(handlingProcessId, HandlingStrategy.RETRY, cause, retries); // passar para paramtros
		}
		else {
			WorkItemHandlerRuntimeException wihRe = new WorkItemHandlerRuntimeException(cause);
			for (String key : handlerInfoMap.keySet()) {
				wihRe.setInformation(key, handlerInfoMap.get(key));
			}
			wihRe.setInformation(WorkItemHandlerRuntimeException.WORKITEMHANDLERTYPE, this.getClass().getSimpleName());
			throw wihRe;
		}
	}
	
 
	
/**
 * TODO: https://github.com/bbalakriz/excep-handler/blob/e1a50cc4b3e8b66e2821c7fffb86690b1fee5b88/src/main/java/com/myspace/handling_wih_exception/ErrornousWorkItemHandler.java
*//*	protected void handleException(Throwable cause, Map<String, Object> handlerInfoMap) {
		if (handlingProcessId != null && handlingStrategy != null) {
			throw new ProcessWorkItemHandlerException(handlingProcessId, handlingStrategy, cause, retries);
		}

		String service = (String) handlerInfoMap.get("Interface");
		String operation = (String) handlerInfoMap.get("Operation");

		if (this.logThrownException) {
			String message;
			if (service != null) {
				message = this.getClass().getSimpleName() + " failed when calling " + service + "." + operation;
			} else {
				message = this.getClass().getSimpleName() + " failed while trying to complete the task.";
			}
			logger.error(message, cause);
		} else {
			WorkItemHandlerRuntimeException wihRe = new WorkItemHandlerRuntimeException(cause);
			for (String key : handlerInfoMap.keySet()) {
				wihRe.setInformation(key, handlerInfoMap.get(key));
			}
			wihRe.setInformation(WorkItemHandlerRuntimeException.WORKITEMHANDLERTYPE, this.getClass().getSimpleName());
			throw wihRe;
		}
	}*/

}
