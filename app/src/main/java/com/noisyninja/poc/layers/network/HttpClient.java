package com.noisyninja.poc.layers.network;

import android.content.Context;

import com.noisyninja.poc.layers.UtilModule;

import java.io.IOException;

import javax.inject.Inject;

import dagger.Module;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.noisyninja.poc.BuildConfig.BASE_URL;

/**
 * Http client
 * Created by sudiptadutta on 27/04/18.
 */


@Module
public class HttpClient {

    private static Retrofit retrofit = null;
    private Context mContext;
    private UtilModule mUtilModule;

    @Inject
    public HttpClient(Context context, UtilModule utilModule) {
        mContext = context;
        mUtilModule = utilModule;
    }

    Retrofit getClient() {

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    //.addInterceptor(new ForceCacheInterceptor())//for network cache
                    //.cache(new Cache(mContext.getCacheDir(), Long.parseLong(CACHE_SIZE)))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public class ForceCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if (!mUtilModule.isNetworkConnected()) {
                builder.cacheControl(CacheControl.FORCE_CACHE);
            }

            return chain.proceed(builder.build());
        }
    }

    /**
     * to bypass certificate errors, //todo not for production
     *
     * @return OkHttpClient.Builder with a empty trust manager
     * @throws RuntimeException
    private OkHttpClient.Builder getUnsafeOkHttpClient() throws RuntimeException {
    try {
    final TrustManager[] trustAllCerts = new TrustManager[]{
    new X509TrustManager() {
    @Override public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override public java.security.cert.X509Certificate[] getAcceptedIssuers() {
    return new java.security.cert.X509Certificate[]{};
    }
    }
    };

    // Install the all-trusting trust manager
    final SSLContext sslContext = SSLContext.getInstance("SSL");
    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
    // Create an ssl socket factory with our all-trusting manager
    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
    builder.hostnameVerifier(new HostnameVerifier() {
    @Override public boolean verify(String hostname, SSLSession session) {
    return true;
    }
    });

    return builder;
    } catch (Exception e) {
    throw new RuntimeException(e);
    }
    }
     */
}
