package com.example.lizardswine.Model

data class DangNhap(
    val TenDangNhap: String,
    val MatKhau: String,
)

data class DangKy(
    val TenDangNhap: String,
    val MatKhau: String,
    val HoTen: String,
    val SoDienThoai: String,
    val Email: String,
    val NgaySinh: String
)

data class TaiKhoan(
    val AnhDaiDien: String,
    val HoTen: String,
    val SoDienThoai: String,
)

data class NguoiDungResponse(
    val status: Int, // you can include the response status
    val data: TaiKhoan
)




