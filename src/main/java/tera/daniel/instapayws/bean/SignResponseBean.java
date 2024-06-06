package tera.daniel.instapayws.bean;

public class SignResponseBean {
	private String status;
	private String businessMsgId;
	private String errorMessage;
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessMsgId() {
		return businessMsgId;
	}

	public void setBusinessMsgId(String businessMsgId) {
		this.businessMsgId = businessMsgId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
