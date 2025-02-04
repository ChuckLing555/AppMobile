package com.example.lizardswine.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lizardswine.Data.UserStore
import com.example.lizardswine.View.CaiDatTaiKhoan
import com.example.lizardswine.View.CapNhatDiaChi
import com.example.lizardswine.View.ChiTietDonHang
import com.example.lizardswine.View.ChiTietSanPham
import com.example.lizardswine.View.Custom_Compose.ChiTietDonHangND
import com.example.lizardswine.View.DSDiaChi
import com.example.lizardswine.View.DangKy
import com.example.lizardswine.View.DangNhap
import com.example.lizardswine.View.DanhGiaSanPham
import com.example.lizardswine.View.DanhSachDanhGia
import com.example.lizardswine.View.DoiMatKhau
import com.example.lizardswine.View.DonDaMua
import com.example.lizardswine.View.GioHang
import com.example.lizardswine.View.Loading
import com.example.lizardswine.View.ManHinhChinh
import com.example.lizardswine.View.ManHinhTimKiem
import com.example.lizardswine.View.NgonNgu
import com.example.lizardswine.View.PhuongThucThanhToan
import com.example.lizardswine.View.SuaHoSo
import com.example.lizardswine.View.SuaSoDienThoai
import com.example.lizardswine.View.SuaTen
import com.example.lizardswine.View.TaiKhoanBaoMat
import com.example.lizardswine.View.ThanhToan
import com.example.lizardswine.View.WelcomeToLizardWine
import com.example.lizardswine.View.XemChiTietDanhMucSP
import com.example.lizardswine.ViewModel.RuouViewModel


//Navigation User Screen
sealed class Screen(val route: String){
    data object WelcomeToLizard: Screen("welcome_to_lizard")
    data object DangNhap: Screen("user_dang_nhap")
    data object DangKy: Screen("user_dang_ky")
    data object ManHinhChinh: Screen("man_hinh_chinh")
    data object GioHang: Screen("gio_hang")
    data object ChiTietRuou: Screen("chi_tiet_ruou")
    data object DSRuouTheoDanhMuc: Screen("danh_sach_ruou_theo_danh_muc")
    data object ThanhToan: Screen("thanh_toan")
    data object DSDanhGiaRuou: Screen("danh_sach_danh_gia_ruou")
    data object XemDSDanhGia: Screen("xem_danh_sach_danh_gia")
    data object CaiDat: Screen("cai_dat")
    data object TaiKhoanVaBaoMat: Screen("tai_khoan_va_bao_mat")
    data object DSDiaChi: Screen("danh_sach_dia_chi_nguoi_dung")
    data object ViDienTu: Screen("vi_dien_tu_da_lien_ket")
    data object HoSoNguoiDung: Screen("xem_ho_so")
    data object SuaSDT: Screen("cap_nhat_so_dien_thoai_nguoi_dung")
    data object SuaTen: Screen("cap_nhat_ten_nguoi_dung")
    data object SuaMatKhau: Screen("cap_nhat_mat_khau_nguoi_dung")
    data object DonDaMua: Screen("xem_don_hang_da_mua")
    data object CapNhatDiaChi: Screen("cap_nhat_dia_chi_nguoi_dung")
    data object Loading: Screen("man_hinh_load")
    data object ManHinhTimKiem: Screen("man_hinh_tim_kiem_san_pham")
    data object ChiTietDonND:Screen("chi_tiet_don_hang_nd")
    data object NgonNgu:Screen("ngon_ngu")
}

@Composable
fun UserNavGraph(navHostController: NavHostController){

    val context = LocalContext.current
    val store = UserStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    NavHost(
        navController = navHostController,
        startDestination = Screen.WelcomeToLizard.route
    ) {
        composable(Screen.WelcomeToLizard.route) {
            if (tokenText.value?.isEmpty() == true)
                WelcomeToLizardWine(navHostController)
            else {
                val user = store.getUser().collectAsState(initial = null)
                if (user.value?.userID != -1)
                    ManHinhChinh(navHostController, user.value?.userID ?: -1)
                else
                    DangNhap(navHostController)
            }
        }
        composable(Screen.DangKy.route){
            DangKy(navHostController)
        }
        composable(Screen.DangNhap.route + "?message={message}",
            arguments = listOf(
                navArgument("message"){nullable = true}
            )){
            var message = it.arguments?.getString("message")
            if(message != null)
                DangNhap(navHostController, message)
            else
                DangNhap(navHostController)
        }
        composable(Screen.ManHinhChinh.route + "?MaND={MaND}",
            arguments = listOf(navArgument("MaND"){nullable = true})
        ){
            var maND = it.arguments?.getString("MaND")
            if(maND != null)
            ManHinhChinh(navHostController, maND.toInt())
        }
        composable(Screen.GioHang.route){
            GioHang(navHostController)
        }
        composable(Screen.ChiTietRuou.route + "?MaR={MaR}",
            arguments = listOf(
                navArgument("MaR"){nullable = true}
            )
            ){
            var MaR = it.arguments?.getString("MaR")
            if(MaR != null)
            ChiTietSanPham(navHostController, MaR.toInt())
        }
        composable(
            Screen.DSRuouTheoDanhMuc.route + "?MaLoaiR={MaLoaiR}&OtherParam={TenDanhMuc}",
            arguments = listOf(
                navArgument("MaLoaiR"){nullable = true},
                navArgument("TenDanhMuc") { nullable = true }
            )
        ) {
            var MaLoaiR = it.arguments?.getString("MaLoaiR")
            var TenDanhMuc = it.arguments?.getString("TenDanhMuc")
            if(MaLoaiR != null)
                XemChiTietDanhMucSP(navHostController, MaLoaiR.toInt(), TenDanhMuc.toString())
        }
        composable(Screen.ThanhToan.route){
            ThanhToan(navHostController)
        }
        composable(Screen.DSDanhGiaRuou.route){
            DanhGiaSanPham(navHostController)
        }
        composable(Screen.XemDSDanhGia.route){
            DanhSachDanhGia(navHostController)
        }
        composable(Screen.CaiDat.route + "?MaND={MaND}",
            arguments = listOf(
                navArgument("MaND"){nullable = true}
            ))
        {
            var MaND = it.arguments?.getString("MaND")

            if(MaND != null)
                CaiDatTaiKhoan(navHostController, MaND.toInt())
        }
        composable(Screen.TaiKhoanVaBaoMat.route + "?MaND={MaND}",
            arguments = listOf(
                navArgument("MaND"){nullable = true}
            )){
            var MaND = it.arguments?.getString("MaND")
            if (MaND != null)
                TaiKhoanBaoMat(navHostController, MaND.toInt())
        }
        composable(Screen.DSDiaChi.route + "?MaND={MaND}",
            arguments = listOf(
                navArgument("MaND"){nullable=true}
            )){
            val MaND = it.arguments?.getString("MaND")
            if(MaND != null)
                DSDiaChi(navHostController, MaND.toInt())
        }
        composable(Screen.ViDienTu.route){
            PhuongThucThanhToan(navHostController)
        }
        composable(Screen.HoSoNguoiDung.route + "?MaND={MaND}",
            arguments = listOf(
                navArgument("MaND"){nullable = true}
            )){
            var MaND = it.arguments?.getString("MaND")
            if(MaND != null)
                SuaHoSo(navHostController, MaND.toInt())
        }
        composable(Screen.SuaSDT.route){
            SuaSoDienThoai(navHostController)
        }
        composable(Screen.SuaTen.route + "?MaND={MaND}",
            arguments =  listOf(
                navArgument("MaND"){nullable = true}
            )){
            val MaND = it.arguments?.getString("MaND")
            if(MaND != null)
                SuaTen(navHostController, MaND.toInt())
        }
        composable(Screen.SuaMatKhau.route){
            DoiMatKhau(navHostController)
        }
        composable(Screen.DonDaMua.route + "?MaND={MaND}",
            arguments =  listOf(
                navArgument("MaND"){nullable = true}
            )){
            val MaND = it.arguments?.getString("MaND")
            if(MaND != null)
                DonDaMua(navHostController, MaND.toInt())
        }
        composable(Screen.CapNhatDiaChi.route + "?MaND={MaND}",
            arguments = listOf(
                navArgument("MaND"){nullable = true}
            ))
        {
            val MaND = it.arguments?.getString("MaND")
            if(MaND!=null)
                CapNhatDiaChi(navHostController, MaND.toInt())
        }
        composable(Screen.Loading.route + "?TenDangNhap={TenDangNhap}&MatKhau={MatKhau}",
            arguments = listOf(
                navArgument("TenDangNhap"){nullable = true},
                navArgument("MatKhau") { nullable = true }
            )
        ){
            var TenDangNhap = it.arguments?.getString("TenDangNhap")
            var MatKhau = it.arguments?.getString("MatKhau")
            if(TenDangNhap != null && MatKhau != null)
                Loading(navHostController, TenDangNhap, MatKhau)
        }
        composable(Screen.ChiTietDonND.route + "?MaHD={MaHD}",
            arguments =  listOf(
                navArgument("MaHD"){nullable=true}
            )) {
            val MaHD = it.arguments?.getString("MaHD")
            if(MaHD != null)
                ChiTietDonHangND(navHostController, MaHD.toInt())
        }
        composable(Screen.ManHinhTimKiem.route){
            ManHinhTimKiem(navHostController)
        }
        composable(Screen.NgonNgu.route) {
            NgonNgu(navHostController)
        }

    }
}
