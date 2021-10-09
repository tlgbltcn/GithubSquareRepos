package com.tlgbltcn.githubsquarerepos.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tlgbltcn.githubsquarerepos.R

@Composable
fun ErrorView(message: String?) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .height(200.dp)
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.padding(all = 20.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_warning_24),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier.padding(all = 20.dp),
                    text = message ?: ""
                )
            }
        }
    }
}