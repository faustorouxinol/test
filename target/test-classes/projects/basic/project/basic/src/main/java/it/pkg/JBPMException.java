package it.pkg;

import java.util.UUID;

@SuppressWarnings("serial")
public class JBPMException extends RuntimeException {

	private String uuid = UUID.randomUUID().toString();

	public JBPMException() {
		this("Non Defined Message (please use constructor)");
	}

	public JBPMException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		return "Exception [uuid:" + uuid + ", msg:" + super.getMessage() + "]";
	}
}