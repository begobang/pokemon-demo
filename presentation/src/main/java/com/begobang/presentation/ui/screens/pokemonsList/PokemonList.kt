package com.begobang.presentation.ui.screens.pokemonsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.begobang.domain.business.PokemonBusiness
import com.begobang.presentation.R
import com.begobang.presentation.ui.PokemonListViewModel
import com.begobang.presentation.ui.composables.Separator
import com.begobang.presentation.ui.theme.PokemonTheme
import java.util.*

@Composable
fun PokemonList(viewModel: PokemonListViewModel = hiltViewModel()){

    val state by viewModel.state.collectAsState()

    PokemonListScreen(
        loading = state.loading,
        pokemonList = state.pokemon?.results,
        error = state.error,
        onClick = {
            //viewModel.navigateToDetail(it)
        }
    )
}

@Composable
fun PokemonListScreen(loading: Boolean, pokemonList: List<PokemonBusiness>? = emptyList(), error: String? = null, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (loading) {
            CircularProgressIndicator()
        }

        pokemonList?.let {
            if(pokemonList.isNotEmpty()){
                LazyColumn(
                    contentPadding = PaddingValues(16.dp)) {
                    items(items = pokemonList) {
                        PokemonItemScreen(
                            item = it,
                            modifier = Modifier.clickable {
                                onClick(it.name)
                            }
                        )
                    }
                }

            }
        }

    }

}


@Composable
fun PokemonItemScreen(
    item: PokemonBusiness?,
    modifier: Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, top = 4.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            AsyncImage(
                model = item?.url,
                contentDescription = item?.name,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .height(70.dp)
                    .clip(MaterialTheme.shapes.medium),
                placeholder = painterResource(R.drawable.ic_pokemon),
                error = painterResource(R.drawable.ic_pokemon)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
            ){
                Text(
                    text = item?.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                        ?: "",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }


        }

        Separator(color = Color.LightGray)
    }


}

@Preview
@Composable
fun ItemPreview(){
    PokemonTheme {
        PokemonItemScreen(PokemonBusiness("Paco", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"), modifier = Modifier.clickable {  })
    }
}
