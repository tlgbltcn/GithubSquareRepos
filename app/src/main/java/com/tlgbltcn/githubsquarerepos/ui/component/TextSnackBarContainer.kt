/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tlgbltcn.githubsquarerepos.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun TextSnackBarContainer(
    snackBarText: String,
    showSnackBar: Boolean,
    onDismissSnackBar: () -> Unit,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable () -> Unit
) {
    Box(modifier) {
        content()

        val onDismissState by rememberUpdatedState(onDismissSnackBar)
        LaunchedEffect(showSnackBar, snackBarText) {
            if (showSnackBar) {
                try {
                    snackBarHostState.showSnackbar(
                        message = snackBarText,
                        duration = SnackbarDuration.Short
                    )
                } finally {
                    onDismissState()
                }
            }
        }

        // Override shapes to not use the ones coming from the MdcTheme
        MaterialTheme(shapes = Shapes()) {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .systemBarsPadding()
                    .padding(all = 8.dp),
            ) {
                Snackbar(it)
            }
        }
    }
}
