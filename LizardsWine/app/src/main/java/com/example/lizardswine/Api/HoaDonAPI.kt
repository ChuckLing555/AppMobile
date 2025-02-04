package com.example.lizardswine.Api

import com.example.lizardswine.Model.HoaDon
import com.google.ai.client.generativeai.type.content
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

object Constant{
    //const val BASE_URL = "http://192.168.1.6/lizardwine_api/api/ds_loai_ruou.php"
    const val BASE_URL = "https://8f0f-2001-ee0-53f2-1000-312b-cd0b-e944-a53a.ngrok-free.app/lizardwine_api/api/"
}

object HoaDonRetrofitClient{
    val hoaDonAPIService : HoaDonAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create(GsonBuilder().create()))
            .build()
            .create(HoaDonAPIService::class.java)
    }
}


data class ApiResponse<T>(
    val data: T?,
    val message: String? = null
)

interface HoaDonAPIService{
    @GET("HoaDon/ds_hoa_don_theo_tthd.php")
    suspend fun layDSHoaDonTheoTrangThai(
        @Query("MaTrangThai") hoadonMaTrangThai: Int
    ): ApiResponse<List<HoaDon>>

    @GET("HoaDon/chi_tiet_hoa_don.php")
    suspend fun layChiTietHoaDonTheoMaHD(
        @Query("MaHD") hoadonMaHD: Int
    ): Response<HoaDon>

    @GET("HoaDon/cap_nhat_trang_thai.php")
    suspend fun capNhatTrangThaiHDTheoMaHD(
        @Query("MaHD") hoadonMaHD: Int
    ): List<String>

    @GET("HoaDon/cap_nhat_trang_thai_huy.php")
    suspend fun capNhatTrangThaiHuyTheoMaHD(
        @Query("MaHD") hoadonMaHD: Int
    ): List<String>

    @GET("HoaDon/lay_hoa_don_theo_trangthaiND.php")
    suspend fun layHoaDonNguoiDung(
        @Query("MaND") MaND: Int,
        @Query("MaTrangThai") MaTrangThai: Int
    ): ApiResponse<List<HoaDon>>
}


class HoaDonRepository {
    private val apiService = HoaDonRetrofitClient.hoaDonAPIService

    suspend fun layDSHoaDon(maTrangThai: Int): Result<List<HoaDon>> {
        return try {
            val response = apiService.layDSHoaDonTheoTrangThai(maTrangThai)

            if (response.message != null) {
                // Trường hợp trả về thông báo lỗi
                Result.failure(Exception(response.message))
            } else {
                // Trả về danh sách hóa đơn
                Result.success(response.data ?: emptyList())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun layChiTietHoaDon(maHD: Int): Result<HoaDon> {
        return try {
            // Gọi API và chuyển đổi kết quả
            val response = apiService.layChiTietHoaDonTheoMaHD(maHD)
            if (response.isSuccessful) {
                // Nếu thành công, trả về dữ liệu
                val hoaDon = response.body()
                if (hoaDon != null) {
                    Result.success(hoaDon)
                } else {
                    Result.failure(Exception("Dữ liệu hóa đơn rỗng!"))
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

    suspend fun capNhatTrangThai(maHD: Int): List<String> {
        return try {apiService.capNhatTrangThaiHDTheoMaHD(maHD)
        } catch (e: Exception) {
            // Xử lý lỗi trong quá trình gọi API
            throw Exception("Có lỗi xảy ra khi cập nhật trạng thái: ${e.message}")
        }
    }

    suspend fun capNhatTrangThaiHuy(maHD: Int): List<String> {
        return try {apiService.capNhatTrangThaiHuyTheoMaHD(maHD)
        } catch (e: Exception) {
            // Xử lý lỗi trong quá trình gọi API
            throw Exception("Có lỗi xảy ra khi cập nhật trạng thái: ${e.message}")
        }
    }

    suspend fun layHoaDonNguoiDung(MaND: Int, MaTrangThai: Int) : Result<List<HoaDon>>{
        return try {
            val response = apiService.layHoaDonNguoiDung(MaND, MaTrangThai)

            if (response.message != null) {
                // Trường hợp trả về thông báo lỗi
                Result.failure(Exception(response.message))
            } else {
                // Trả về danh sách hóa đơn
                Result.success(response.data ?: emptyList())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun capNhatTrangThaiHuyND(MaND: Int): List<String> {
        return try {apiService.capNhatTrangThaiHuyTheoMaHD(MaND)
        } catch (e: Exception) {
            // Xử lý lỗi trong quá trình gọi API
            throw Exception("Có lỗi xảy ra khi cập nhật trạng thái: ${e.message}")
        }
    }

}
