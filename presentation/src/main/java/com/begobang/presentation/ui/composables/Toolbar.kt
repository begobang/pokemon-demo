package com.begobang.presentation.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.begobang.presentation.ui.PokemonScreen

@Composable
fun Toolbar(
    toolbarName: String,
    showUpNavigation: Boolean,
    showAction: Boolean,
    content: @Composable (padding: PaddingValues) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = toolbarName,
                        style = MaterialTheme.typography.h1
                    )
                },
                navigationIcon = {
                    if(showUpNavigation){
                        AppBarIcon(
                            imageVector = Icons.Default.ArrowBack,
                            onClick = {  },
                            contentDescription = "back"
                        )
                    }
                },
                actions = {
                    if(showAction){
                        AppBarIcon(
                            imageVector = Icons.Filled.Search,
                            onClick = {

                            },
                            contentDescription = "search"
                        )
                    }

                }
            )
        }
    ) {
        content(it)
    }
}

@Composable
fun AppBarIcon(imageVector: ImageVector, onClick: () -> Unit, contentDescription: String? = null) {
    IconButton(onClick = onClick) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    PokemonScreen {
        Toolbar(toolbarName = "Hello world", showUpNavigation = true, showAction = true) {}
    }
}