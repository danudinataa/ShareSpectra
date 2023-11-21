package com.ramaa.narutowiki.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.ramaa.narutowiki.R
import com.ramaa.narutowiki.domain.model.Character
import com.ramaa.narutowiki.presentation.common.CharacterList
import com.ramaa.narutowiki.presentation.common.SearchBar
import com.ramaa.narutowiki.presentation.navgraph.Route
import com.ramaa.narutowiki.util.Dimens.Padding1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(characters: LazyPagingItems<Character>, navigate:(String) -> Unit) {

    val titles by remember {
        derivedStateOf {
            if (characters.itemCount > 10) {
                characters.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.name }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Padding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_naruto_wiki_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Padding1)
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(Padding1))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = Padding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigate(Route.SearchScreen.route)
            }
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(Padding1))

        Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = Padding1)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(Padding1))

        CharacterList(
            modifier = Modifier.padding(horizontal = Padding1),
            characters = characters,
            onClick = {
                //TODO: Navigate to Details Screen
            }
        )
    }
}