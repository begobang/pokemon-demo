package com.begobang.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.presentation.R
import com.begobang.presentation.ui.theme.PokemonTheme
import com.begobang.presentation.ui.theme.Yellow500
import com.begobang.presentation.ui.theme.Yellow700

@Composable
fun ProfileImage(url: String, name: String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(500)
            .build(),
        contentDescription = name,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .fillMaxHeight(0.5f)
            .padding(end = 16.dp)
            .background(MaterialTheme.colors.primary),
        placeholder = painterResource(R.drawable.ic_pokemon),
        error = painterResource(R.drawable.ic_pokemon)
    )
}

@Composable
fun Header(
    item: PokemonDetailBusiness,
    content: @Composable () -> Unit
) {
        Card(modifier = Modifier.fillMaxWidth().padding(16.dp), elevation = 8.dp) {
            Row {
                content()
                Column(modifier = Modifier.padding(top = 40.dp)) {
                    Title(name = item.name, baseExperience = "${item.baseExperience}")
                    Dimensions(height = item.height, weight = item.weight)

                }

            }
        }


    }

@Composable
fun Title(name: String, baseExperience: String){
    Row(modifier = Modifier.padding(top = 16.dp)) {
        NameLabel(name = name, style = MaterialTheme.typography.h2, modifier = Modifier.padding(end = 8.dp))
        Text(text = baseExperience, style = MaterialTheme.typography.body2, fontStyle = FontStyle.Italic, color = Yellow700, modifier = Modifier.padding(end = 4.dp))
        Icon(imageVector = Icons.Default.StarBorder, contentDescription = "Star", tint = Yellow500)
    }

}

@Composable
fun Dimensions(height: Int, weight: Int){
    Column(modifier = Modifier.padding(top = 12.dp)) {
        Text(text = "H: $height ft" )
        Text(text = "W: $weight lb")
    }
}

@Preview
@Composable
fun DimensionTest(){
    PokemonTheme {
        Dimensions(7, 190)
    }
}