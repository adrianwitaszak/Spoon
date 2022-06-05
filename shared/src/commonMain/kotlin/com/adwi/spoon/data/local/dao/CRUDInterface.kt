package com.adwi.spoon.data.local.dao

interface CRUDInterface<T> {

    fun getAll(): List<T>
    fun getByID(id: String): T
    fun add(item: T)
    fun delete(id: String)
}