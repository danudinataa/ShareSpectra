package com.ramaa.pmobile_uas.presentation.navigation.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.ui.theme.ShareSpectraTheme
import com.ramaa.pmobile_uas.util.Dimens.ExtraSmallPadding2
import com.ramaa.pmobile_uas.util.Dimens.IconSize

@Composable
fun AppBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.contentDescription,
                            modifier = Modifier.size(IconSize),
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                ),
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String,
    val contentDescription: String
)

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    ShareSpectraTheme(dynamicColor = false) {
        AppBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_24, text = "Home", contentDescription = "home_page"),
            BottomNavigationItem(icon = R.drawable.baseline_newspaper_24, text = "News", contentDescription = "news_page"),
            BottomNavigationItem(icon = R.drawable.baseline_search_24, text = "Search", contentDescription = "search_page"),
            BottomNavigationItem(icon = R.drawable.baseline_bookmarks_24, text = "Bookmark", contentDescription = "bookmark_page"),
            BottomNavigationItem(icon = R.drawable.baseline_person_24, text = "Profile", contentDescription = "about_page")
        ), selectedItem = 0, onItemClick = {})
    }
}