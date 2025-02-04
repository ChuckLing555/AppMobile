package com.example.lizardswine.Model

data class Anh(val MaHA: Int, val AnhRuou: String)

data class Ruou(
    val MaR: Int,
    val MaLoaiR: Int,
    val AnhRuou: List<Anh>,
    val TenRuou: String,
    val XuatXu: String,
    val NongDoCon: Int,
    val DungTich: Int,
    val NamSanXuat: Int,
    val ThanhPhan: String,
    val HuongVi: String,
    val GiaBan: Int,
    val SoLuongTon: Int,
    val DiemDG: Int,
    val MoTa: String,
    val TrangThai: Int
)