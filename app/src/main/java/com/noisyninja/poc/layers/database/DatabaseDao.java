package com.noisyninja.poc.layers.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.noisyninja.poc.model.Customer;
import com.noisyninja.poc.model.Table;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by sudiptadutta on 30/04/18.
 */

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM customers")
    Flowable<List<Customer>> getAll();

    @Query("SELECT * FROM customers where id LIKE  :id")
    Single<Customer> findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Customer> customers);

    @Query("SELECT * FROM tables")
    Single<List<Table>> getAllTable();

    @Query("SELECT * FROM tables where customerID LIKE  :customerID")
    Flowable<List<Table>> findTablesByCustomerID(int customerID);

    @Query("SELECT * FROM tables where id LIKE  :id")
    Single<Table> findTableByID(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTable(Table table);

    @Delete
    void deleteTable(Table table);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTable(List<Table> tableList);

}