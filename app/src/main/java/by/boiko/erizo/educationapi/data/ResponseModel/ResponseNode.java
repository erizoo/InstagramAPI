package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNode {

    @SerializedName("thumbnail_src")
    private String src;
    @SerializedName("thumbnail_resources")
    private List<ResponseResources> resources;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<ResponseResources> getResources() {
        return resources;
    }

    public void setResources(List<ResponseResources> resources) {
        this.resources = resources;
    }
}
