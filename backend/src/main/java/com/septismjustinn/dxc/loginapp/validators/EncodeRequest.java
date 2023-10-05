package com.septismjustinn.dxc.loginapp.validators;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class EncodeRequest {
    @NotNull
    @Length(min=1, max=1)
    private String offset;

    @NotNull
    @Length(min=1)
    private String plainText;

    public EncodeRequest(@NotNull String offset, @NotNull String plainText) {
        this.offset = offset;
        this.plainText = plainText;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
}
