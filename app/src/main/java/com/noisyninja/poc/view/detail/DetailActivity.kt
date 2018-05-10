package com.noisyninja.poc.view.detail

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.noisyninja.poc.BuildConfig.APPLICATION_ID
import com.noisyninja.poc.R
import com.noisyninja.poc.layers.di.NinjaInjector.ninjaComponent
import com.noisyninja.poc.model.Table
import com.noisyninja.poc.view.BaseActivity
import com.noisyninja.poc.view.interfaces.IDetailActivity
import com.noisyninja.poc.view.interfaces.IDetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : BaseActivity(), IDetailActivity {

    private var mResultList: ArrayList<Table> = ArrayList()
    private lateinit var mIDetailPresenter: IDetailPresenter
    private lateinit var mReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val customerID = intent.getIntExtra("customerID", 0)

        mIDetailPresenter = DetailPresenter(this, ninjaComponent, customerID)

        setupUI()

    }

    private fun setupUI() {
        //region UI
        recyclerListDetail.layoutManager = GridLayoutManager(this, 4)
        recyclerListDetail.adapter = DetailAdapter(this, mResultList, mIDetailPresenter)
        recyclerListDetail.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        recyclerListDetail.addItemDecoration(DividerItemDecoration(this, LinearLayout.HORIZONTAL))
    }


    override fun setTables(result: ArrayList<Table>?) {
        mResultList.clear()
        if (result != null) {
            mResultList.addAll(result)
            refresh()
            handleShowError(false, null)
        } else {
            handleShowError(true, Exception(ninjaComponent.resources().getString(R.string.error_net)))
        }
    }

    override fun refresh() {
        ninjaComponent.util().logI("refresh tables")

        runOnUiThread {
            val adapter = recyclerListDetail.adapter as DetailAdapter
            adapter.notifyDataSetChanged()
        }
    }

    /**
     * show an error message if loading fails
     */
    private fun handleShowError(isError: Boolean, t: Throwable?) {
        runOnUiThread({
            if (isError) {
                recyclerListDetail.visibility = View.GONE
                recyclerTextDetail.visibility = View.VISIBLE
                recyclerTextDetail.text = t?.message
            } else {
                recyclerListDetail.visibility = View.VISIBLE
                recyclerTextDetail.visibility = View.GONE
            }
        })
    }

    public fun done(view: View) {
        ninjaComponent.util().logI("done")
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        mReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                mIDetailPresenter.getTables()
            }
        }
        registerReceiver(this.mReceiver, IntentFilter(APPLICATION_ID))

        mIDetailPresenter.getTables()
    }

    override fun onPause() {
        unregisterReceiver(mReceiver)
        super.onPause()
    }
}