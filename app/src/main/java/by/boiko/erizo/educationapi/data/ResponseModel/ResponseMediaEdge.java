package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMediaEdge {

    @SerializedName("count")
    private int count;
    @SerializedName("page_info")
    private ResponseInfo pageInfo;
    @SerializedName("edges")
    private List<ResponseEdge> edgeList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResponseEdge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<ResponseEdge> edgeList) {
        this.edgeList = edgeList;
    }

    public ResponseInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(ResponseInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
