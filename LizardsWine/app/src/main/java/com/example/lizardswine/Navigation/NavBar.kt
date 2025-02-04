package com.example.lizardswine.Navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.lizardswine.R
import com.example.lizardswine.View.LichSuMuaHang
import com.example.lizardswine.View.Loading
import com.example.lizardswine.View.ThongBao
import com.example.lizardswine.View.ThongTinNguoiDung
import com.example.lizardswine.View.TrangChu
import com.example.lizardswine.ViewModel.RuouViewModel

//Navigation
sealed class NavBarItem(val route: String, var icon: Int, var lable: String){

    data object TrangChu: NavBarItem("trang_chu", R.drawable.homeblack, lable = "Trang chủ")
    data object LichSu: NavBarItem("lich_su", R.drawable.history, "Lịch sử")
    data object ThongBao: NavBarItem("thong_bao", R.drawable.bell, "Thông báo")
    data object TaiKhoan: NavBarItem("tai_khoan", R.drawable.user, "Tài khoản")
}

@Composable
fun NavGraphBar(navHostController: NavHostController, navRootController: NavHostController, MaND: Int){

    NavHost(
        navController = navHostController,
        startDestination = NavBarItem.TrangChu.route
    ) {
        composable(NavBarItem.TrangChu.route
        ){
                TrangChu(navRootController)
        }
        composable(NavBarItem.LichSu.route){
            LichSuMuaHang(navRootController)
        }
        composable(NavBarItem.ThongBao.route){
            ThongBao()
        }
        composable(NavBarItem.TaiKhoan.route ,
        ){
            ThongTinNguoiDung(navRootController, MaND)
        }
    }
}

@Composable
fun NavAppBar(navHostController: NavHostController){
    val items = listOf(
        NavBarItem.TrangChu,
        NavBarItem.LichSu,
        NavBarItem.ThongBao,
        NavBarItem.TaiKhoan
    )

    var selectedItem by remember { mutableIntStateOf(0) }
    val selectedIcon = listOf(R.drawable.home, R.drawable.historyblack, R.drawable.bellblack, R.drawable.userblack)

    NavigationBar( containerColor = Color.White
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource( if(selectedItem == index) selectedIcon[index] else item.icon),
                        contentDescription = null,
                        tint = if(selectedItem == index) Color(0xFF004D40) else Color.Black
                    )
                },
                label = {
                    Text(item.lable, color = if(selectedItem == index) Color(0xFF188158) else Color.Black)
                },
                selected = currentRoute?.hierarchy?.any(){
                    it.route == item.route
                } == true,
                onClick = {
                    selectedItem = index
                    navHostController.navigate(item.route){
                        navHostController.graph.startDestinationRoute?.let {
                            popUpTo(it){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
        }
    }
}