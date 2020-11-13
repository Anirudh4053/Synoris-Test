package com.anirudh.synoristestapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LocalDocData(
    @PrimaryKey(autoGenerate = true)
    var dId: Int,
    var abstractValue: String,
    var articleType: String? = "",
    var id: String,
    var publicationDate: String,
    var titleDisplay: String?=""
) {
    constructor() : this(0, "","","","","")
}