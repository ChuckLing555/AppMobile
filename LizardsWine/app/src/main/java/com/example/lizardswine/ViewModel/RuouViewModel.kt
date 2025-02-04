package com.example.lizardswine.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lizardswine.Api.RuouRepository
import com.example.lizardswine.Model.HoaDon
import com.example.lizardswine.Model.LoaiRuou
import com.example.lizardswine.Model.Ruou
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RuouViewModel: ViewModel(){
    private val repository = RuouRepository()

    private val _loairuouList = MutableStateFlow<List<LoaiRuou>>(emptyList())
    val loairuouList: StateFlow<List<LoaiRuou>> get() = _loairuouList

    private val _ruouTheoMaList = MutableStateFlow<List<Ruou>>(emptyList())
    val ruouTheoMaList: StateFlow<List<Ruou>> get() = _ruouTheoMaList

    private val _tatCaRuouList = MutableStateFlow<List<Ruou>>(emptyList())
    val tatCaRuouList: StateFlow<List<Ruou>> get() = _tatCaRuouList

    private val _timKiemRuouList = MutableStateFlow<List<Ruou>>(emptyList())
    val timKiemRuouList: StateFlow<List<Ruou>> get() = _timKiemRuouList

    private val _ruouTheoMa = MutableStateFlow<Result<Ruou>?>(null)
    val ruouTheoMa: StateFlow<Result<Ruou>?> = _ruouTheoMa

    private val _message = MutableStateFlow<String?>(null) // Lưu thông báo trả về từ API
    val message: StateFlow<String?> get() = _message

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun getEmptyList(): StateFlow<List<Ruou>>{
        return MutableStateFlow(emptyList())
    }

    fun layDanhMucRuou() {
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layDanhMucRuou()
                if (result.isSuccess) {
                    _loairuouList.value = result.getOrNull() ?: emptyList()
                } else {
                    _loairuouList.value = emptyList()
                }
            } catch (e: Exception) {
                _loairuouList.value = emptyList() // Xóa dữ liệu nếu lỗi
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun layTatCaRuou() {
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layTatCaRuou()
                if (result.isSuccess) {
                    _tatCaRuouList.value = result.getOrNull() ?: emptyList()
                    _message.value = null
                } else {
                    _tatCaRuouList.value = emptyList()
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _tatCaRuouList.value = emptyList() // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun layDSRuouTheoMaLoaiR(maLoaiR: Int){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layDSRuouTheoMaLoai(maLoaiR)
                if (result.isSuccess) {
                    _ruouTheoMaList.value = result.getOrNull() ?: emptyList()
                    _message.value = null
                } else {
                    _ruouTheoMaList.value = emptyList()
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _ruouTheoMaList.value = emptyList() // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun layChiTietRuouTheoMaR(maR: Int){
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.layChiTietRuou(maR)
                if (result.isSuccess) {
                    _ruouTheoMa.value = repository.layChiTietRuou(maR)
                    _message.value = null
                } else {
                    _ruouTheoMa.value = null
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _ruouTheoMa.value = null // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun timKiemRuou(value: String): StateFlow<List<Ruou>> {
        viewModelScope.launch(Dispatchers.IO){
            _isLoading.value = true
            try {
                val result = repository.timKiemRuou(value)
                if (result.isSuccess) {
                    _timKiemRuouList.value = result.getOrNull() ?: emptyList()
                    _message.value = null
                } else {
                    _timKiemRuouList.value = emptyList()
                    _message.value = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                _timKiemRuouList.value = emptyList() // Xóa dữ liệu nếu lỗi
                _message.value = e.message // Lưu thông báo lỗi hoặc từ API
            } finally {
                _isLoading.value = false
            }
        }
        return timKiemRuouList
    }


}