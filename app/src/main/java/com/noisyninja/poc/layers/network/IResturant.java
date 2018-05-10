package com.noisyninja.poc.layers.network;

import com.noisyninja.poc.model.Customer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.noisyninja.poc.BuildConfig.CUSTOMER_URI;
import static com.noisyninja.poc.BuildConfig.TABLE_URI;

/**
 * retrofit interface for moviedb calls
 * Created by sudiptadutta on 27/04/18.
 */

public interface IResturant {

    @GET(CUSTOMER_URI)
    Observable<List<Customer>> getCustomers();

    @GET(TABLE_URI)
    Observable<List<Boolean>> getTables();

}
