package edu.wgu.d387_sample_code.model.response;

import java.util.Map;

public class WelcomeResponse {
    private Map<String, String> messages;

    public WelcomeResponse() {}

    public WelcomeResponse(Map<String, String> messages) {
        this.messages = messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }
    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
}
