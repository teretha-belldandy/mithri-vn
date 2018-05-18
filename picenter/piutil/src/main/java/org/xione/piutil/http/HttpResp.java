package org.xione.piutil.http;

public class HttpResp {

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public byte[] getHttpContent() {
		return httpContent;
	}

	public void setHttpContent(byte[] httpContent) {
		this.httpContent = httpContent;
	}

	private Integer httpStatus;
	private String encoding;
	private byte[] httpContent;

}
