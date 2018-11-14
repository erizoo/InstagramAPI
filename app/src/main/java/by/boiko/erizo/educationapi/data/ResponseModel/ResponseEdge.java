package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class ResponseEdge {

    @SerializedName("node")
    private ResponseNode node;

    public ResponseNode getNode() {
        return node;
    }

    public void setNode(ResponseNode node) {
        this.node = node;
    }
}
