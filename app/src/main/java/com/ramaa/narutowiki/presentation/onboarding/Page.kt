package com.ramaa.narutowiki.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ramaa.narutowiki.R

data class Page(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = R.string.title_page1,
        description = R.string.desc_page1,
        image = R.drawable.onboarding1
    ),
    Page(
        title = R.string.title_page2,
        description = R.string.desc_page2,
        image = R.drawable.onboarding2
    ),
    Page(
        title = R.string.title_page3,
        description = R.string.desc_page3,
        image = R.drawable.onboarding3
    )
)