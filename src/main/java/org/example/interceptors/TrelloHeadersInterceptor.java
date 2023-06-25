package org.example.interceptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TrelloHeadersInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("token", "140f3dd7e297b0a11906e71f55a835296304f59f8601e81ad419fa14de959176")
                .build();
        return chain.proceed(request);
    }
}
