package com.juansenen.gaticketapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Clase para el envio de request a chatGPT
 */
public class ChatRequest {

    private String model;
    private List<MessageGpt> messages;
    private double temperature;
    private int max_tokens;

    public ChatRequest(){}

    public ChatRequest(String model, List<MessageGpt> messages, double temperature, int max_tokens) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<MessageGpt> getMessagesGpt() {
        return messages;
    }

    public void setMessages(List<MessageGpt> messages) {
        this.messages = messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public static class MessageGpt {
        private String role;
        private String content;

        // Constructor, getters y setters
        public MessageGpt(){}
        public MessageGpt(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


}

