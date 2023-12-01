package com.juansenen.gaticketapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespuestaModel {
    @SerializedName("id")
    private String id;

    @SerializedName("object")
    private String object;

    @SerializedName("created")
    private long created;

    @SerializedName("model")
    private String model;

    @SerializedName("system_fingerprint")
    private String systemFingerprint;

    @SerializedName("choices")
    private List<Choice> choices;

    @SerializedName("usage")
    private Usage usage;

    public static class Choice {
        @SerializedName("index")
        private int index;

        @SerializedName("message")
        private Message message;

        @SerializedName("finish_reason")
        private String finishReason;

        public Message getMessage() {
            return message;
        }

        public String getFinishReason() {
            return finishReason;
        }
    }

    public static class Message {
        @SerializedName("role")
        private String role;

        @SerializedName("content")
        private String content;

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }

    public static class Usage {
        @SerializedName("prompt_tokens")
        private int promptTokens;

        @SerializedName("completion_tokens")
        private int completionTokens;

        @SerializedName("total_tokens")
        private int totalTokens;

        public int getPromptTokens() {
            return promptTokens;
        }

        public int getCompletionTokens() {
            return completionTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public String getSystemFingerprint() {
        return systemFingerprint;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }
}
