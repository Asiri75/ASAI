package com.libertytech.asai.ui

import com.libertytech.asai.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Recipes : BottomNavItem("Recipes", R.drawable.ic_recipes, "recipes")
    object Lukas : BottomNavItem("Lukas", R.drawable.ic_lukas, "lukas")
    object Maxime : BottomNavItem("Maxime", R.drawable.ic_maxime,"Maxime")

    object Benoit: BottomNavItem("Benoit", R.drawable.ic_dollar_benoit, "benoit")
    object Marius: BottomNavItem("Marius", R.drawable.baseline_question_answer_24, "marius")
    object Aymeric : BottomNavItem("Aymeric", R.drawable.ic_aymeric,"Aymeric")
}