package com.ramaa.narutowiki.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.ramaa.narutowiki.R
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.presentation.common.CharacterList
import com.ramaa.narutowiki.util.Dimens.Padding1

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (ItemCharacter) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = Padding1, start = Padding1, end = Padding1)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(Padding1))

        CharacterList(
            itemCharacters = state.itemCharacters,
            onClick = navigateToDetails
        )
    }
}