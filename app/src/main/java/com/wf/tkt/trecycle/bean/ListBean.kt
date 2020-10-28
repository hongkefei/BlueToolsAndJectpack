package com.wf.tkt.trecycle.bean

data class ListBean(
    val error: Boolean,
    val results: ArrayList<Result>
)

data class Result(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String
)