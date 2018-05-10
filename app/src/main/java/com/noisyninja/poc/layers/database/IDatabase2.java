package com.noisyninja.poc.layers.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.noisyninja.poc.model.Customer;
import com.noisyninja.poc.model.Table;

/**
 * Created by sudiptadutta on 30/04/18.
 */

@Database(entities = {Customer.class, Table.class}, version = 1)
public abstract class IDatabase2 extends RoomDatabase {
    public abstract DatabaseDao2 databaseDao2();
}
