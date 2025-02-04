package com.example.lizardswine.Model

data class DiaChi(
    val MaDC: Int,
    val MaND: Int,
    val DCGiaoHang : String,
    val TrangThai: Int,
    val IsDefault: Int
)

data class DiaChiRequest(
    val MaND: Int,
    val SoNha: String,
    val TenDuong: String,
    val TenXa: String,
    val TenHuyen: String,
    val TenTinh: String,
)


