package com.example.lizardswine.Api

import androidx.lifecycle.viewModelScope
import com.example.lizardswine.Model.DangKy
import com.example.lizardswine.Model.DiaChi
import com.example.lizardswine.Model.DiaChiRequest
import com.example.lizardswine.Model.HoaDon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

object DangNhapRetrofitClient {
    private const val BASE_URL = "https://8f0f-2001-ee0-53f2-1000-312b-cd0b-e944-a53a.ngrok-free.app/lizardwine_api/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val nguoiDungAPIService: NguoiDungAPISevice by lazy {
        retrofit.create(NguoiDungAPISevice::class.java)
    }
}

sealed class NguoiDungApiResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NguoiDungApiResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : NguoiDungApiResponse<T>(data, message)
    class Loading<T> : NguoiDungApiResponse<T>()
}

interface NguoiDungAPISevice {
    @POST("NguoiDung/dang_nhap.php")
    suspend fun dangNhap(@Body request: DangNhapRequest): NguoiDungResponse<ThongTinND>

    @POST("NguoiDung/them_nguoi_dung.php")
    suspend fun dangKy(@Body dangKy: DangKy): DangKyResponse<ThongTinND>

    @GET("NguoiDung/thong_tin_nguoi_dung.php")
    suspend fun layThongTinTaiKhoan(
        @Query("MaND") MaND: Int
    ): Response<ThongTinND>

    @GET("DiaChi/ds_dia_chi_theo_maND.php")
    suspend fun layDSDiaChi(
        @Query("MaND") MaND: Int
    ): ApiResponse<List<DiaChi>>

    @GET("NguoiDung/sua_ten.php")
    suspend fun suaTen(
        @Query("MaND") MaND: Int,
        @Query("HoTen") HoTen: String
    ): Response<String>

    @POST("DiaChi/them_dia_chi.php")
    suspend fun themDiaChi(@Body diaChi: DiaChiRequest): ThemDiaChiResponse<DiaChi>

}
data class DangNhapRequest(
    val TenDangNhap: String,
    val MatKhau: String
)

data class NguoiDungResponse<T>(
    val error: String? = null,
    val user: T?
)

data class DangKyResponse<T>(
    val status: Boolean,
    val message: String? = null,
    val data: T?
)

data class ThemDiaChiResponse<T>(
    val status: Boolean,
    val message: String? = null,
    val data: T?
)

data class ThongTinND(
    val MaND: Int,
    val TenDangNhap: String,
    val MatKhau: String,
    val HoTen: String,
    val Email: String,
    val SoDienThoai: String,
    val NgaySinh: String,
    val AnhDaiDien: String?
)

class DangNhapRepository {
    private val apiService = DangNhapRetrofitClient.nguoiDungAPIService

    suspend fun dangNhap(tenDangNhap: String, matKhau: String): Result<ThongTinND> {
        return try {
            // Gọi API và chuyển đổi kết quả
            val response = apiService.dangNhap(DangNhapRequest(tenDangNhap, matKhau))
            // Kiểm tra lỗi
            if (!response.error.isNullOrEmpty()) {
                Result.failure(Exception(response.error))
            } else if (response.user == null) {
                Result.failure(Exception("Không tìm thấy thông tin người dùng"))
            } else {
                Result.success(response.user)
            }
        } catch (e: Exception) {
            // Bắt lỗi bất kỳ trong quá trình gọi API
            Result.failure(e)
        }
    }

    suspend fun layThongTinNguoiDung(maND: Int): Result<ThongTinND> {
        return try {
            // Gọi API và chuyển đổi kết quả
            val response = apiService.layThongTinTaiKhoan(maND)
            if (response.isSuccessful) {
                // Nếu thành công, trả về dữ liệu
                val thongtin = response.body()
                if (thongtin != null) {
                    Result.success(thongtin)
                } else {
                    Result.failure(Exception("Thông tin người dùng không tồn tại!"))
                }
            } else {
                // Nếu thất bại, trả về lỗi từ server
                Result.failure(Exception("Lỗi từ máy chủ: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Bắt lỗi bất kỳ trong quá trình gọi API
            Result.failure(e)
        }
    }

    suspend fun suaHoTen(MaND: Int, HoTen: String): Result<String> {
        return try {
            val response = apiService.suaTen(MaND, HoTen)
            Result.success(response.body()?: "")
        } catch (e: Exception) {
            // Xử lý lỗi trong quá trình gọi API
            throw Exception("Có lỗi xảy ra khi cập nhật trạng thái: ${e.message}")
        }
    }

    suspend fun layDiaChiNguoiDung(MaND: Int): Result<List<DiaChi>> {
        return try {
            val response = apiService.layDSDiaChi(MaND)

            if (response.message != null) {
                // Trường hợp trả về thông báo lỗi
                Result.failure(Exception(response.message))
            } else {
                // Trả về danh sách địa chỉ
                Result.success(response.data ?: emptyList())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
