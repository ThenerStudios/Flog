package com.thenerstudios.flog.Modal;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("error")
    private boolean err;

    @SerializedName("message")
    private String msg;

    private User user;

    public DefaultResponse(boolean err, String msg, User user) {
        this.err = err;
        this.msg = msg;
        this.user = user;
    }


    public boolean isErr() {
        return err;
    }

    public String getMsg() {
        return msg;
    }

    public User getUser() {
        return user;
    }
}
