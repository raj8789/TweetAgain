package com.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class TWConfiguration extends Configuration {
    @NotNull
    private MessageQueueFactory messageQueue = new MessageQueueFactory();

    @JsonProperty("messageQueue")
    public MessageQueueFactory getMessageQueueFactory() {
        return messageQueue;
    }

    @JsonProperty("messageQueue")
    public void setMessageQueueFactory(MessageQueueFactory factory) {
        this.messageQueue = factory;
    }

}
