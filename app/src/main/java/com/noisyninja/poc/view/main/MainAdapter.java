package com.noisyninja.poc.view.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.noisyninja.poc.R;
import com.noisyninja.poc.databinding.ListMainBinding;
import com.noisyninja.poc.model.Customer;
import com.noisyninja.poc.view.interfaces.IMainPresenter;

import java.util.List;

/**
 * Created by sudiptadutta on 28/04/18.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomerViewHolder> {

    private List<Customer> mResultsList;
    private IMainPresenter mMainPresenter;

    public MainAdapter(List<Customer> resultsList, IMainPresenter mainPresenter) {
        this.mResultsList = resultsList;
        this.mMainPresenter = mainPresenter;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ListMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_main, viewGroup, false);
        return new CustomerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder movieViewHolder, int position) {
        final Customer customer = mResultsList.get(position);
        movieViewHolder.bind(customer, mMainPresenter);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.list_main;
    }

    @Override
    public int getItemCount() {
        return mResultsList.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {
        private ListMainBinding mListMainBinding;

        CustomerViewHolder(ListMainBinding listMainBinding) {
            super(listMainBinding.getRoot());
            mListMainBinding = listMainBinding;
        }

        void bind(@NonNull Customer results, @NonNull IMainPresenter mainPresenter) {
            mListMainBinding.setCustomer(results);
            mListMainBinding.setMainPresenter(mainPresenter);
            mListMainBinding.executePendingBindings();
        }
    }
}
