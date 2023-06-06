package com.libertytech.asai.ui

import com.libertytech.asai.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Lukas : BottomNavItem("Lukas", R.drawable.ic_lukas, "lukas")
    object Benoit: BottomNavItem("Benoit", R.drawable.ic_dollar_benoit, "benoit")
}