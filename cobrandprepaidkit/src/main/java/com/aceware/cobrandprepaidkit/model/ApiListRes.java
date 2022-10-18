package com.aceware.cobrandprepaidkit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiListRes {
    @SerializedName("base_url")
    private String base_url;

    @SerializedName("data")
    private List<Data>data;

    public class Data
    {
        @SerializedName("method")
        private String method;

        @SerializedName("api")
        private String api;

        @SerializedName("params")
        private List<String>params;

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }
}
