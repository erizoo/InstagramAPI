package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class ResponseUser {

    @SerializedName("edge_owner_to_timeline_media")
    private ResponseMediaEdge mediaEdge;
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResponseMediaEdge getMediaEdge() {
        return mediaEdge;
    }

    public void setMediaEdge(ResponseMediaEdge mediaEdge) {
        this.mediaEdge = mediaEdge;
    }
}
