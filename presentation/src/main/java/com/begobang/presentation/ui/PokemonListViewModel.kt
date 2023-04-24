package com.begobang.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.begobang.domain.GetPokemons
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemons: GetPokemons
): ViewModel() {

    private val _state = MutableStateFlow(PokemonState())
    val state = _state.asStateFlow()

    init {
        getPokemons()
    }

    fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = PokemonState(loading = true)
            getPokemons(Unit).fold(
                ::handleError,
                ::handleSuccess
            )
        }

    }

    private fun handleError(failure: Failure){
        _state.value = PokemonState(loading = false, error = failure.exception)
    }

    private fun handleSuccess(business: PokemonsBusiness?){
        _state.value = PokemonState(loading = false, pokemon = business)
    }

}


data class PokemonState(
    val loading: Boolean = false,
    val pokemon: PokemonsBusiness? = null,
    val error: String? = null
)