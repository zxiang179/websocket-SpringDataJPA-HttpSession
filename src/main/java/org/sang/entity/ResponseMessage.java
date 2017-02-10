package org.sang.entity;

/**
 * Created by sang on 16-12-22.
 */
public class ResponseMessage {
	//服务器返回给浏览器的消息由这个类来承载
    private String responseMessage;

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
