package com.noisyninja.poc.layers.database

import android.arch.persistence.room.Room
import android.content.Context
import com.noisyninja.poc.BuildConfig.APP_DB
import com.noisyninja.poc.layers.UtilModule
import com.noisyninja.poc.layers.Utils
import com.noisyninja.poc.model.Customer
import com.noisyninja.poc.model.Table
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * databse client
 * Created by sudiptadutta on 30/04/18.
 */
class DataBaseModule @Inject constructor(internal var mUtilModule: UtilModule, context: Context) : DatabaseDao {

    internal var mDataBase: IDatabase

    init {

        Utils.logI(this.javaClass, "DataBaseModule")
        mDataBase = Room.databaseBuilder(context,
                IDatabase::class.java, APP_DB)
                //.allowMainThreadQueries() do not use!!
                .build()
    }

    override fun getAll(): Flowable<List<Customer>> {
        return mDataBase.databaseDao().all
    }

    override fun findById(id: Int): Single<Customer> {

        return mDataBase.databaseDao().findById(id)
    }

    override fun delete(results: Customer) {
        // Completable.fromAction { mDataBase.databaseDao.delete(results) } //non verbose short hand

        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                mDataBase.databaseDao().delete(results)
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {}

            override fun onComplete() {
                mUtilModule.logI("delete done")
            }

            override fun onError(e: Throwable) {
                mUtilModule.logI("delete error")
            }
        })
    }

    override fun insert(results: Customer) {
        // Completable.fromAction { mDataBase.databaseDao().insert(results) } //non verbose short hand

        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                mDataBase.databaseDao().insert(results)
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {}

            override fun onComplete() {
                mUtilModule.logI("insert done")
            }

            override fun onError(e: Throwable) {
                mUtilModule.logI("insert error")
            }
        })
    }

    override fun insertAll(customers: List<Customer>?) {
        // Completable.fromAction { mDataBase.databaseDao().insertAll(customers) } //non verbose short han
        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                mUtilModule.logI("inserting")
                mDataBase.databaseDao().insertAll(customers)
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {}

            override fun onComplete() {
                mUtilModule.logI("insert done")
            }

            override fun onError(e: Throwable) {
                mUtilModule.logI("insert error")
            }
        })
    }

    override fun getAllTable(): Single<MutableList<Table>> {
        return mDataBase.databaseDao().allTable
    }

    override fun findTablesByCustomerID(customerID: Int): Flowable<MutableList<Table>> {
        return mDataBase.databaseDao().findTablesByCustomerID(customerID)
    }

    override fun findTableByID(id: Int): Single<Table> {
        return mDataBase.databaseDao().findTableByID(id)
    }

    override fun insertTable(table: Table?) {

        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                mDataBase.databaseDao().insertTable(table)
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {}

            override fun onComplete() {
                mUtilModule.logI("insert table done")
            }

            override fun onError(e: Throwable) {
                mUtilModule.logI("insert table error")
            }
        })
    }

    override fun deleteTable(table: Table?) {
        Completable.fromAction { mDataBase.databaseDao().deleteTable(table) } //non verbose short hand
    }

    override fun insertAllTable(tableList: List<Table>?) {
        Completable.fromAction(object : Action {
            @Throws(Exception::class)
            override fun run() {
                mDataBase.databaseDao().insertAllTable(tableList)
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {}

            override fun onComplete() {
                mUtilModule.logI("insert AllTable done")
            }

            override fun onError(e: Throwable) {
                mUtilModule.logI("insert AllTable error")
            }
        })
    }

}