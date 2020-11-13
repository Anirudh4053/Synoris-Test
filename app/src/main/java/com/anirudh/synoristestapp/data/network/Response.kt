package com.anirudh.synoristestapp.data.network
import com.google.gson.annotations.SerializedName


data class ResponseData(
    @SerializedName("response")
    val response: Res
)

data class Res(
    @SerializedName("docs")
    val docs: List<DocData>,
    @SerializedName("maxScore")
    val maxScore: Double,
    @SerializedName("numFound")
    val numFound: Int,
    @SerializedName("start")
    val start: Int
)

data class DocData(
    @SerializedName("abstract")
    val abstract: List<String>,
    @SerializedName("article_type")
    val articleType: String,
    @SerializedName("author_display")
    val authorDisplay: List<String>,
    @SerializedName("eissn")
    val eissn: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("journal")
    val journal: String,
    @SerializedName("publication_date")
    val publicationDate: String,
    @SerializedName("score")
    val score: Double,
    @SerializedName("title_display")
    val titleDisplay: String
)