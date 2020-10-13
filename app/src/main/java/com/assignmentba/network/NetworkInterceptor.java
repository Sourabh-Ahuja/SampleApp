package com.assignmentba.network;

import android.content.Context;
import android.util.Log;


import com.assignmentba.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>
 * Observes, modifies, and potentially short-circuits requests going out and the corresponding
 * responses coming back in. Typically interceptors add, remove, or transform headers on the request
 * or response.
 */
public class NetworkInterceptor implements Interceptor {

    private Context context;

    public NetworkInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        if (new NetworkUtils(context).isNetworkConnected()) {
            request.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 60)
                    .build();
        } else {
            Log.d("NetworkInterceptor : ", "No internet");
        }

        return chain.proceed(request);
    }
}
