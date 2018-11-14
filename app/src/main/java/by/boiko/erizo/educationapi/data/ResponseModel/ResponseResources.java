package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class ResponseResources {

    @SerializedName("src")
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
