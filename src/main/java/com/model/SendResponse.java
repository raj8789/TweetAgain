package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendResponse {
    /**.
     * tweet value store
     */
    private String message;

    /**
     * Used to send response on tweet posted.
     *
     * @param messages is a response message.
     */
    public SendResponse(final String messages) {
        this.message = messages;
    }
}
