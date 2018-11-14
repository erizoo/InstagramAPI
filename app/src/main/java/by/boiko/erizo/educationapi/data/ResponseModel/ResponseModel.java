package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("entry_data")
    private ResponseEntryData entryData;
    @SerializedName("config")
    private ResponseConfig config;

    public ResponseEntryData getEntryData() {
        return entryData;
    }

    public void setEntryData(ResponseEntryData entryData) {
        this.entryData = entryData;
    }

    public ResponseConfig getConfig() {
        return config;
    }

    public void setConfig(ResponseConfig config) {
        this.config = config;
    }
}
