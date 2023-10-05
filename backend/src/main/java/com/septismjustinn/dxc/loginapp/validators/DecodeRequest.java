package com.septismjustinn.dxc.loginapp.validators;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class DecodeRequest {
    @NotNull
    @Length(min=1)
    private String plainText;

    // This annotation is important for deserializing single-attribute requests for some reason.
    @JsonCreator
    public DecodeRequest(@NotNull String plainText) {
        this.plainText = plainText;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
}
