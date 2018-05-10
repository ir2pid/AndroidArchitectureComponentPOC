package com.noisyninja.poc.view.detail

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noisyninja.poc.R
import com.noisyninja.poc.databinding.ListDetailBinding
import com.noisyninja.poc.model.Table
import com.noisyninja.poc.view.interfaces.IDetailPresenter


/**
 * Created by sudiptadutta on 28/04/18.
 */

public class DetailAdapter(private val mContext: Context, private val mResultsList: ArrayList<Table>, private val mDetailPresenter: IDetailPresenter) : RecyclerView.Adapter<DetailAdapter.TableViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TableViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ListDetailBinding>(inflater, R.layout.list_detail, viewGroup, false)
        return TableViewHolder(binding)
    }

    override fun onBindViewHolder(tableViewHolder: TableViewHolder, position: Int) {
        val table = mResultsList.get(position)
        tableViewHolder.bind(table, mDetailPresenter)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.list_main
    }

    override fun getItemCount(): Int {
        return mResultsList.size
    }

    inner class TableViewHolder(private val mListDetailBinding: ListDetailBinding) : RecyclerView.ViewHolder(mListDetailBinding.root) {

        fun bind(results: Table, detailPresenter: IDetailPresenter) {
            mListDetailBinding.results = results
            mListDetailBinding.detailPresenter = detailPresenter
            mListDetailBinding.executePendingBindings()
        }
    }
}
