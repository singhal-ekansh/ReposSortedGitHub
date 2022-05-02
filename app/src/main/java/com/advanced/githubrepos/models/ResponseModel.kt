package com.advanced.githubrepos.models

data class ResponseModel(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)