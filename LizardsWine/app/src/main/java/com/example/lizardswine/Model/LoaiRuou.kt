package com.example.lizardswine.Model

data class LoaiRuou (
    val MaLoaiR: String,
    val TenLoaiRuou: String,
    val MaLoaiCha: String?,
    val TrangThai: String,
    val DanhMucCon: List<LoaiRuou>
)