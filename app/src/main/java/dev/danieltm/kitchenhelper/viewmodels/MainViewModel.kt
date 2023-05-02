package dev.danieltm.kitchenhelper.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.lifecycle.ViewModel
import dev.danieltm.kitchenhelper.models.BottomNavItem

class MainViewModel : ViewModel() {
    fun getNavList() : List<BottomNavItem>
    {
        var navItems = listOf(
            BottomNavItem(
                name = "Recipes",
                route = "home",
                icon = Icons.Default.Home
            ),
            BottomNavItem(
                name = "",
                route = "addIngredient",
                icon = Icons.Default.AddCircle
            ),
            BottomNavItem(
                name = "INGREDIENTS",
                route = "ingredients",
                icon = Icons.Default.Menu,
                badgeCount = 7
            )
        )
        return navItems
    }
}