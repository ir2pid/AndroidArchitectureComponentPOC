package com.noisyninja.poc.layers.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.noisyninja.poc.model.Customer;

import java.util.List;

/**
 * Created by sudiptadutta on 30/04/18.
 */

@Dao
public interface DatabaseDao2 {

    @Query("SELECT * FROM customers")
    LiveData<List<Customer>> getAll();

    @Query("SELECT * FROM customers where id LIKE  :id")
    LiveData<Customer> findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Customer> customers);

}