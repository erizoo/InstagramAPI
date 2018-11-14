package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEntryData {

    @SerializedName("ProfilePage")
    private List<ResponseProfilePage> page;

    public List<ResponseProfilePage> getPage() {
        return page;
    }

    public void setPage(List<ResponseProfilePage> page) {
        this.page = page;
    }
}
