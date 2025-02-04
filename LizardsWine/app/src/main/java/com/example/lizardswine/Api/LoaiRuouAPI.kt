package com.example.lizardswine.Api

import com.example.lizardswine.Model.HoaDon
import com.example.lizardswine.Model.LoaiRuou
import com.example.lizardswine.Model.Ruou
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//ds_loai_ruou.php

object RetrofitClient {

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("ds_loai_ruou.php") // Đường dẫn endpoint (sửa theo API của bạn)
    suspend fun layDanhMucRuou(): ApiResponse<List<LoaiRuou>>

    @GET("Ruou/ds_ruou_theo_ma_loai.php")
    suspend fun layDSRuouTheoMaLoaiR(
        @Query("MaLoaiR") MaLoaiR: Int
    ): ApiResponse<List<Ruou>>

    @GET("Ruou/chi_tiet_ruou.php")
    suspend fun layChiTietRuouTheoMa(
        @Query("MaR") MaR: Int
    ): Response<Ruou>

    @GET("Ruou/ds_ruou.php")
    suspend fun layTatCaRuou(): ApiResponse<List<Ruou>>

    @GET("Ruou/tim_kiem_ruou.php")
    suspend fun timKiemRuou(
        @Query("value") value: String
    ): ApiResponse<List<Ruou>>
}

class RuouRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun layDanhMucRuou(): Result<List<LoaiRuou>> {
        return try {
            val response = apiService.layDanhMucRuou()

            Result.success(response.data ?: emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun layTatCaRuou(): Result<List<Ruou>> {
        return try {
            val response = apiService.layTatCaRuou()

            Result.success(response.data ?: emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun layChiTietRuou(maR: Int): Result<Ruou> {
        return try {
            // Gọi API và chuyển đổi kết quả
            val response = apiService.layChiTietRuouTheoMa(maR)
            if (response.isSuccessful) {
                // Nếu thành công, trả về dữ liệu
                val ruou = response.body()
                if (ruou != null) {
                    Result.success(ruou)
                } else {
                    Result.failure(Exception("Dữ liệu rượu rỗng!"))
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

    suspend fun layDSRuouTheoMaLoai(maLoaiR: Int): Result<List<Ruou>> {
        return try {
            val response = apiService.layDSRuouTheoMaLoaiR(maLoaiR)

            Result.success(response.data ?: emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun timKiemRuou(value: String): Result<List<Ruou>> {
        return try {
            val response = apiService.timKiemRuou(value)
            Result.success(response.data ?: emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}