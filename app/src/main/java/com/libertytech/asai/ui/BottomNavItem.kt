package com.libertytech.asai.ui

import com.libertytech.asai.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Recipes : BottomNavItem("Recipes", R.drawable.ic_recipes, "recipes")
    object Benoit: BottomNavItem("Benoit", R.drawable.ic_dollar_benoit, "benoit")
}