package com.ramaa.pmobile_uas.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.presentation.detail.components.DetailsCompanyTopBar
import com.ramaa.pmobile_uas.util.Dimens
import com.ramaa.pmobile_uas.util.Dimens.Padding1

@Composable
fun AboutScreen(
    navigateUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(Padding1),  // Keep padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DetailsCompanyTopBar(
            onBackClick = navigateUp
        )

        Text(
            text = "About Us",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = Padding1)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = Padding1)
        )

        Text(
            text = stringResource(id = R.string.app_description),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = Padding1)
        )

        // Team Member Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Padding1),
            verticalArrangement = Arrangement.spacedBy(Padding1)
        ) {
            items(teamMembers.size) { index ->
                TeamMemberCard(teamMember = teamMembers[index])
            }
        }
    }
}

@Composable
fun TeamMemberCard(teamMember: TeamMember) {
    Column(
        modifier = Modifier
            .padding(Padding1)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = teamMember.photoResourceId),
            contentDescription = teamMember.name,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Text(
            text = teamMember.name,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = Padding1)
        )
    }
}

val teamMembers = listOf(
    TeamMember("Rifky Halsandrian", R.drawable.aldi),
    TeamMember("Muhamad Aziz Prasetyo", R.drawable.aziz),
    TeamMember("Rama Danudinata", R.drawable.rama),
    TeamMember("Andhika Danus Saputra", R.drawable.danus)
)

data class TeamMember(val name: String, val photoResourceId: Int)

