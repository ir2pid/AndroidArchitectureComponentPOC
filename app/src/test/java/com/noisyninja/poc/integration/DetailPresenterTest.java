package com.noisyninja.poc.integration;

import com.noisyninja.poc.layers.di.NinjaComponent;
import com.noisyninja.poc.model.Table;
import com.noisyninja.poc.view.detail.DetailPresenter;
import com.noisyninja.poc.view.interfaces.IDetailActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * DetailPresenter Test
 * Created by sudiptadutta on 01/05/18.
 */

public class DetailPresenterTest {

    private DetailPresenter detailPresenter;
    @Mock
    private
    IDetailActivity iDetailActivity;
    @Mock
    private
    NinjaComponent NinjaComponent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        detailPresenter = new DetailPresenter(iDetailActivity, NinjaComponent, 1);
    }


    @Test
    public void onSuccessTest() {
        ArrayList<Boolean> arrayList = new ArrayList<>();
        detailPresenter.onSuccess(arrayList);
        verify(iDetailActivity).setTables(Matchers.<ArrayList<Table>>any());
    }

    @Test
    public void getTablesTest() {
        detailPresenter.getTables();
        verify(NinjaComponent).database();
    }

    @Test
    public void onErrorTest() {
        detailPresenter.onError(new Exception("test"));
        verify(NinjaComponent).database();
    }
}
