package com.example.lizardswine.Model

import com.google.gson.annotations.SerializedName

data class HoaDon (
    val MaHD: Int,
    val TenDangNhap: String,
    val SoDienThoai: String,
    val DCGiaoHang: String,
    val ChiTietHoaDon: List<ChiTietHoaDon>,
    val LoaiThanhToan: String,
    val MaTrangThai: Int,
    val LoaitrangThai: String,
    val LoaiKhuyenMai: String,
    val GiaTriKM: Int,
    val NgayLapHD: String,
    val TongTien: Int,
    val TongThanhToan: Int,
    val SoLuongRuou: String
)

data class ChiTietHoaDon(
    val MaR: Int,
    val DS_AnhRuou: List<DS_AnhRuou>,
    val TenRuou: String,
    val SoLuong: Int,// SoLuong: Int
    val GiaBan: Int,
    val ThanhTien: Int
)

data class DS_AnhRuou(
    val MaHA: Int,
    val AnhRuou: String
)