package com.noisyninja.poc.layers.network;

import com.noisyninja.poc.model.Customer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * network module to make network calls
 * Created by sudiptadutta on 27/04/18.
 */

public class NetworkModule {
    private HttpClient mHttpClient;

    @Inject
    public NetworkModule(HttpClient httpClient) {
        mHttpClient = httpClient;
    }

    public void getCustomers(final ICallback iCallback) {
        getCustomerObservable().subscribeWith(getObserver(iCallback));
    }

    public void getTables(final ICallback iCallback) {
        getTableObservable().subscribeWith(getObserver(iCallback));
    }

    private Observable<List<Customer>> getCustomerObservable() {
        return mHttpClient.getClient().create(IResturant.class)
                .getCustomers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Boolean>> getTableObservable() {
        return mHttpClient.getClient().create(IResturant.class)
                .getTables()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private <T> GenericObserver<T> getObserver(final ICallback iCallback) {
        return new GenericObserver<>(iCallback);
    }
}
