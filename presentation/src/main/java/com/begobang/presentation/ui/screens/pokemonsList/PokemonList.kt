package com.begobang.presentation.ui.screens.pokemonsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.begobang.domain.business.PokemonItemBusiness
import com.begobang.presentation.R
import com.begobang.presentation.ui.composables.BaseScreen
import com.begobang.presentation.ui.composables.NameLabel
import com.begobang.presentation.ui.composables.SearchBar
import com.begobang.presentation.ui.composables.Separator

/*
    In every Screen composable called in the navigation component, we need to declare our viewModel
    if required, and its state will be the one recomposing the UI.

    In order to use a "RecyclerView" in Compose we can use LazyColum, LazyRow and LazyGrid. It is much more
    practical to use this kind of composables because we won't need to set an adapter and viewholders.
    We just need to provide the Lazy* the data as items and the itemContent will be set with the UI we
    need for each item. If we had different type of typeView we can create items one after another
    or even separated ones without passing items.
 */
@Composable
fun PokemonList(viewModel: PokemonListViewModel = hiltViewModel()){

    val state by viewModel.state.collectAsState()

    PokemonListScreen(
        loading = state.loading,
        pokemonList = state.pokemon?.results,
        error = state.error,
        onClick = {
            viewModel.navigateToDetail(it)
        }
    ){
        viewModel.getPokemons()
    }
}

@Composable
fun PokemonListScreen(loading: Boolean, pokemonList: List<PokemonItemBusiness>? = emptyList(), error: String? = null, onClick: (String) -> Unit, onRetry: () -> Unit) {

    var search by rememberSaveable { mutableStateOf("") }

    BaseScreen(loading = loading, error = error, onRetry = { onRetry() }) {
        pokemonList?.let {
            if(pokemonList.isNotEmpty()){
                SearchBar(
                    search = search,
                    onValueChange = { search = it },
                    onReset = { search = "" }) { onRetry() }

                LazyColumn(
                    contentPadding = PaddingValues(16.dp)) {
                    items(items = pokemonList.filter { it.name.contains(search, true) }) {
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
    item: PokemonItemBusiness?,
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
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item?.imageUrl)
                    .crossfade(500)
                    .build(),
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
                NameLabel(
                    name = item?.name, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }


        }

        Separator(color = Color.LightGray)
    }


}

@Preview
@Composable
fun ListPreview(){
    PokemonListScreen(loading = false, error = "Unable to resolve host.", onClick =  {}, onRetry = {})
}

