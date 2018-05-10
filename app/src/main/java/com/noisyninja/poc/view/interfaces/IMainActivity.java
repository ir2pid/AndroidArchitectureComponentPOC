package com.noisyninja.poc.view.interfaces;

import com.noisyninja.poc.model.Customer;

import java.util.ArrayList;

/**
 * Created by sudiptadutta on 28/04/18.
 */
public interface IMainActivity {

    void setCustomers(ArrayList<Customer> result);

    void openDetail(int id);
}
