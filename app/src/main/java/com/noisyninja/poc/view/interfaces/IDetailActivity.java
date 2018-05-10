package com.noisyninja.poc.view.interfaces;

import com.noisyninja.poc.model.Table;

import java.util.ArrayList;

/**
 * Created by sudiptadutta on 28/04/18.
 */
public interface IDetailActivity {

    void setTables(ArrayList<Table> result);

    void refresh();
}
