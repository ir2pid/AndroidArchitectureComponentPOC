package com.noisyninja.poc.integration;

import com.noisyninja.poc.layers.di.NinjaComponent;
import com.noisyninja.poc.model.Customer;
import com.noisyninja.poc.view.main.MainActivity;
import com.noisyninja.poc.view.main.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Main Presenter Test
 * Created by sudiptadutta on 01/05/18.
 */

public class MainPresenterTest {

    private MainPresenter mainPresenter;
    @Mock
    private
    MainActivity iMainActivity;
    @Mock
    private
    NinjaComponent NinjaComponent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(iMainActivity, NinjaComponent);
    }


    @Test
    public void onSuccessTest() {
        ArrayList<Customer> arrayList = new ArrayList<>();
        mainPresenter.onSuccess(arrayList);
        verify(iMainActivity).setCustomers(arrayList);
    }

    @Test
    public void getCustomerTest() {
        mainPresenter.getCustomers();
        verify(NinjaComponent).network();
    }

    @Test
    public void onErrorTest() {
        mainPresenter.onError(new Exception("test"));
        verify(NinjaComponent).database();
    }
}
