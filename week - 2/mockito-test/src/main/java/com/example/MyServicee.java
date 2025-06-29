package com.example;

public class MyServicee {
    private ExternalApi externalApi;

    public MyServicee(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }
}
