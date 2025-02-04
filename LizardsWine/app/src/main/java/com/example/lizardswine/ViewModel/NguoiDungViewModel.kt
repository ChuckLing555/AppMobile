package com.example.lizardswine.ViewModel

import android.util.Log
import android.view.View
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.lizardswine.Api.DangNhapRepository
import com.example.lizardswine.Api.NguoiDungApiResponse
import com.example.lizardswine.Api.ThongTinND
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lizardswine.Api.DangNhapRetrofitClient
import com.example.lizardswine.Api.HoaDonRepository
import com.example.lizardswine.Api.NguoiDungResponse
import com.example.lizardswine.Model.DangKy
import com.example.lizardswine.Model.DiaChi
import com.example.lizardswine.Model.DiaChiRequest
import com.example.lizardswine.Model.HoaDon
import kotlinx.coroutines.withContext
import com.example.lizardswine.ViewModel.DangNhapViewModel
import org.json.JSONObject

class DangNhapViewModel : ViewModel() {
    private val repository = DangNhapRepository()

    private val _loginState = MutableStateFlow<Result<ThongTinND>?>(null)
    val loginState: StateFlow<Result<ThongTinND>?> = _loginState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val error: StateFlow<String?> get() = _message


    fun dangNhap(tenDangNhap: String, matKhau: String) {
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.dangNhap(tenDangNhap, matKhau, )
                if (result.isSuccess) {
                    _loginState.value = repository.dangNhap(tenDangNhap, matKhau,)
                    _message.value = null
                } else {
                    _loginState.value = null
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _loginState.value = null // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }

}

class DangKyViewModel : ViewModel() {
    private val _registerState = MutableStateFlow<String?>(null)
    val registerState: StateFlow<String?> get()= _registerState

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val thongBaoLoi: StateFlow<String?> get() = _message

    fun dangKy(TenDangNhap: String, HoTen: String, MatKhau: String, Email: String, SoDienThoai: String, NgaySinh: String) {
        viewModelScope.launch {
            try {
                // Tạo đối tượng `DangKy` để gửi lên API
                val dangKy = DangKy(TenDangNhap, MatKhau, HoTen, SoDienThoai, Email, NgaySinh)
                val response = DangNhapRetrofitClient.nguoiDungAPIService.dangKy(dangKy)
                if (response.status) {
                    val user = response.data
                    if (user != null) {
                        _registerState.value = "Thêm địa chỉ thành công"
                        _message.value = null
                    } else {
                        _registerState.value = "Thêm thất bại."
                        _message.value = response.message
                    }
                } else {
                    _message.value = "Thêm thất bại: ${response.message}"
                }

            } catch (e: Exception) {
                _message.value = "Lỗi: ${e.message}" // Hiển thị lỗi nếu có
            }

        }
    }

}

class LayThongTinNguoiDung : ViewModel(){
    private val repository = DangNhapRepository()

    private val _thongTin = MutableStateFlow<Result<ThongTinND>?>(null)
    val thongTinTheoMa: StateFlow<Result<ThongTinND>?> = _thongTin

    private val _diaChi = MutableStateFlow<Result<List<DiaChi>>?>(null)
    val diaChi: StateFlow<Result<List<DiaChi>>?> = _diaChi

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val error: StateFlow<String?> get() = _message

    fun layThongTin(maND: Int) {
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layThongTinNguoiDung(maND)
                if (result.isSuccess) {
                    _thongTin.value = repository.layThongTinNguoiDung(maND)
                    _message.value = null
                } else {
                    _thongTin.value = null
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _thongTin.value = null // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun layDiaChi(MaND: Int) {
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layDiaChiNguoiDung(MaND)
                if (result.isSuccess) {
                    _diaChi.value = repository.layDiaChiNguoiDung(MaND)
                    _message.value = null
                } else {
                    _diaChi.value = null
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _diaChi.value = null // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }


}

class ThemDiaChi : ViewModel() {
    private val _themdiachi = MutableStateFlow<String?>(null)
    val themdiachi: StateFlow<String?> get()= _themdiachi

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val thongBaoLoi: StateFlow<String?> get() = _message

    fun themDiaChiND(MaND: Int,SoNha: String, TenDuong: String, TenXa: String, TenHuyen: String, TenTinh: String) {
        viewModelScope.launch {
            try {
                val diaChiND = DiaChiRequest(MaND,SoNha,TenDuong, TenXa, TenHuyen, TenTinh)
                val response = DangNhapRetrofitClient.nguoiDungAPIService.themDiaChi(diaChiND)
                if (response.status) {
                    val user = response.data
                    if (user != null) {
                        _themdiachi.value = "Thêm địa chỉ thành công"
                        _message.value = null
                    } else {
                        _themdiachi.value = "Thêm thất bại."
                        _message.value = response.message
                    }
                } else {
                    _message.value = "Thêm thất bại: ${response.message}"
                }
            } catch (e: Exception) {
                _message.value = "Lỗi: ${e.message}"
            }
        }
    }

}

class SuaTenNguoiDung : ViewModel(){
    private val repository = DangNhapRepository()

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val message: StateFlow<String?> get() = _message

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun suaTen(MaND : Int, HoTen: String) {
        viewModelScope.launch (Dispatchers.IO){
            _isLoading.value = true
            try {
                val response = repository.suaHoTen(MaND, HoTen)
                response.fold(
                    onSuccess = { message ->
                        _message.value = message
                    },
                    onFailure = { exception ->
                        _message.value = exception.message ?: "Có lỗi"
                    }
                )
            } catch (e: Exception) {
                // Xử lý lỗi
                _message.value = e.message ?: "Có lỗi xảy ra!"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

