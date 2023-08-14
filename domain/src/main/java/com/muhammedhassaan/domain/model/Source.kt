package com.muhammedhassaan.domain.model

import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo("sourceId")
    val id: String?,
    @ColumnInfo("sourceName")
    val name: String?
){
    constructor(): this(null, null)
}