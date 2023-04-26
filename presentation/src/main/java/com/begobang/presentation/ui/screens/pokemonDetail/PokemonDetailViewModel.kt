package com.begobang.presentation.ui.screens.pokemonDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.begobang.domain.GetPokemonDetail
import com.begobang.domain.business.PokemonDetailBusiness
import com.begobang.domain.failure.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
    In every viewModel that retrieved data to the UI, we need a State data class. Everytime there is
    a change on its state, the composables will recompose and update the UI.

    In this way we can show loading, update data or show an error state in the UI.
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    var getPokemon: GetPokemonDetail
): ViewModel()  {

    private val _state = MutableStateFlow(PokemonDetailState())
    val state = _state.asStateFlow()

    fun getPokemonDetail(id: String = "1"){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = PokemonDetailState(loading = true)
            try {
                getPokemon(id).fold(
                    ::handlePokemonsError,
                    ::handlePokemonsSuccess
                )
            } catch (e: Exception){
                handlePokemonsError(Failure.BaseFailure(message = e.localizedMessage))
            }

        }
    }

    private fun handlePokemonsError(failure: Failure){
        _state.value = PokemonDetailState(loading = false, error = failure.exception)
    }

    private fun handlePokemonsSuccess(business: PokemonDetailBusiness?){
        _state.value = PokemonDetailState(loading = false, pokemon = business)
    }
}

data class PokemonDetailState(
    val loading: Boolean = false,
    val pokemon: PokemonDetailBusiness? = null,
    val error: String? = null
)