package by.boiko.erizo.educationapi.data.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class ResponseProfilePage {

    @SerializedName("graphql")
    private ResponseGraphHql hql;

    public ResponseGraphHql getHql() {
        return hql;
    }

    public void setHql(ResponseGraphHql hql) {
        this.hql = hql;
    }
}
