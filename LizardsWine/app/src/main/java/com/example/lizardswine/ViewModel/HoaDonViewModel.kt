package com.example.lizardswine.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lizardswine.Api.HoaDonRepository
import com.example.lizardswine.Model.HoaDon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HoaDonViewModel: ViewModel(){

    private val repository = HoaDonRepository()

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val message: StateFlow<String?> get() = _message

    private val _hoaDonList = MutableStateFlow<List<HoaDon>>(emptyList())
    val hoaDonList: StateFlow<List<HoaDon>> get() = _hoaDonList

    private val _updateResult = MutableStateFlow<Result<String>?>(null)
    val updateResult: StateFlow<Result<String>?> = _updateResult

    private val _hoaDon = MutableStateFlow<Result<HoaDon>?>(null)
    val hoaDon: StateFlow<Result<HoaDon>?> = _hoaDon

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun layDSHoaDonTheoTrangThai(maTrangThai: Int){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layDSHoaDon(maTrangThai)
                if (result.isSuccess) {
                    _hoaDonList.value = result.getOrNull() ?: emptyList()
                    _message.value = null
                } else {
                    _hoaDonList.value = emptyList()
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _hoaDonList.value = emptyList() // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun layChiTietHoaDonTheoMaHD(maHD: Int){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layChiTietHoaDon(maHD)
                if (result.isSuccess) {
                    _hoaDon.value = repository.layChiTietHoaDon(maHD)
                    _message.value = null
                } else {
                    _hoaDon.value = null
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _hoaDon.value = null // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun capNhatTrangThaiHoaDon(maHD: Int, maTrangThai: Int) {
        viewModelScope.launch (Dispatchers.IO){
            _isLoading.value = true
            try {
                val response = repository.capNhatTrangThai(maHD)

                // Kiểm tra nếu phản hồi hợp lệ (tương tự như trong ViewModel đã nói)
                if (response.isNotEmpty() && response[0] == "message") {
                    // Thành công, gọi load danh sách hóa đơn mới
                    layDSHoaDonTheoTrangThai(maTrangThai)
                } else {
                    _message.value = "Phản hồi không hợp lệ từ server!"
                }
            } catch (e: Exception) {
                // Xử lý lỗi
                _message.value = e.message ?: "Có lỗi xảy ra!"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun capNhatTrangThaiHuyHoaDon(maHD: Int, maTrangThai: Int) {
        viewModelScope.launch (Dispatchers.IO){
            _isLoading.value = true
            try {
                val response = repository.capNhatTrangThaiHuy(maHD)

                // Kiểm tra nếu phản hồi hợp lệ (tương tự như trong ViewModel đã nói)
                if (response.isNotEmpty() && response[0] == "message") {
                    // Thành công, gọi load danh sách hóa đơn mới
                    layDSHoaDonTheoTrangThai(maTrangThai)
                } else {
                    _message.value = "Phản hồi không hợp lệ từ server!"
                }
            } catch (e: Exception) {
                // Xử lý lỗi
                _message.value = e.message ?: "Có lỗi xảy ra!"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

//-------------------------------------------------------------------------------------------
class LayHoaDonNguoiDung : ViewModel(){
    private val repository = HoaDonRepository()

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val message: StateFlow<String?> get() = _message

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _hoaDonNDList = MutableStateFlow<List<HoaDon>>(emptyList())
    val hoaDonNDList: StateFlow<List<HoaDon>> get() = _hoaDonNDList

    private val _hoaDon = MutableStateFlow<Result<HoaDon>?>(null)
    val hoaDon: StateFlow<Result<HoaDon>?> = _hoaDon

    fun dsHoaDonND(MaND: Int, MaTrangThai: Int){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layHoaDonNguoiDung(MaND, MaTrangThai)
                if (result.isSuccess) {
                    _hoaDonNDList.value = result.getOrNull() ?: emptyList()
                    _message.value = null
                } else {
                    _hoaDonNDList.value = emptyList()
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _hoaDonNDList.value = emptyList() // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun capNhatTrangThaiHuyHoaDonND(MaND: Int, maTrangThai: Int) {
        viewModelScope.launch (Dispatchers.IO){
            _isLoading.value = true
            try {
                val response = repository.capNhatTrangThaiHuyND(MaND)
                if (response.isNotEmpty() && response[0] == "message") {
                    dsHoaDonND(MaND,maTrangThai)
                } else {
                    _message.value = "Phản hồi không hợp lệ từ server!"
                }
            } catch (e: Exception) {
                // Xử lý lỗi
                _message.value = e.message ?: "Có lỗi xảy ra!"
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun layChiTietHoaDonTheoMaHDND(MaHD: Int){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layChiTietHoaDon(MaHD)
                if (result.isSuccess) {
                    _hoaDon.value = repository.layChiTietHoaDon(MaHD)
                    _message.value = null
                } else {
                    _hoaDon.value = null
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _hoaDon.value = null // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }
}
