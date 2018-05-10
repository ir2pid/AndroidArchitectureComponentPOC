package com.noisyninja.poc.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sudiptadutta on 27/04/18.
 */

@Entity(tableName = "tables")
data class Table(@field:SerializedName("isOccupied")
                 @field:Expose
                 var isOccupied: Boolean,
                 @field:SerializedName("customerID")
                 @field:Expose
                 var customerID: Int = -1) : BaseDTO() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Expose
    var id: Int = 0

}
