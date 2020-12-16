package com.styni.simplecarbase.data

import com.google.gson.annotations.SerializedName

data class Brand(

    @SerializedName("_id")
    override val id: IdEntity,

    @SerializedName("Brand")
    override val name: String,

    @SerializedName("logo")
    val logo: String,

): Item()

data class Model(

    @SerializedName("_id")
    override val id: IdEntity,

    @SerializedName("Brand")
    val brandId: IdEntity,

    @SerializedName("Model")
    override val name: String,

    @SerializedName("img")
    val img: String,

    @SerializedName("gen")
    val gen: Any?,

    @SerializedName("year")
    val year: Int,

    @SerializedName("engines")
    val engines: Any?
): Item()

data class IdEntity(
    @SerializedName("\$oid")
    val hash: String
)

abstract class Item {
    abstract val id: IdEntity

    abstract val name: String
}


