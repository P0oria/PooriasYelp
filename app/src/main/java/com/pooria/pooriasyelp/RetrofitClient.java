package com.pooria.pooriasyelp;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitClient {

    private static RetrofitClient retrofitClient = null;
    protected API api;

    private RetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer YOUR API KEY")
                                .build());
                    }
                }
        ).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        api = retrofit.create(API.class);
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }

        return retrofitClient;
    }

    public API getApi() {
        return api;
    }
}
