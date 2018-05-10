package com.noisyninja.poc.junit

import com.noisyninja.poc.layers.Utils
import com.noisyninja.poc.model.Table
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Unit tests
 * Created by sudiptadutta on 01/05/18.
 */
class UnitTest {

    lateinit var table: Table
    @Before
    fun setup() {
        table = Table(true, 100)
    }

    @Test
    fun marshall() {
        val json = Utils.toJson(table)
        Assert.assertNotNull(json)
    }

    @Test
    fun unmarshall() {
        val json = Utils.toJson(table)
        Assert.assertEquals(table, Utils.fromJson(json, Table::class.java))
    }
}