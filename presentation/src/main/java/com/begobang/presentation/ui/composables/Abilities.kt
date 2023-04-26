package com.begobang.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.begobang.domain.business.AbilitiesBusiness
import com.begobang.domain.business.PokemonBusiness
import com.begobang.domain.business.TypesBusiness
import com.begobang.presentation.ui.theme.PokemonTheme

@Composable
fun Types(types: List<TypesBusiness>){
    Column(modifier = Modifier.padding(end = 24.dp)) {
        NameLabel(name = "Types", modifier = Modifier.padding(top = 24.dp, start = 20.dp))
        LazyColumn{
            items (types){
                Type(types = it)
            }
        }
    }
}

@Composable
fun Abilities(abilities: List<AbilitiesBusiness>){
    Column(modifier = Modifier.padding(end = 24.dp)) {
        NameLabel(name = "Abilities", modifier = Modifier.padding(start = 20.dp, top = 24.dp))
        LazyColumn{
            items (abilities){
                Ability(ability = it)
            }
        }
    }

}
@Composable
fun Type(types: TypesBusiness){
    Row {
        Checkbox(checked = true, onCheckedChange = {  })
        NameLabel(name = types.type.name, style = MaterialTheme.typography.h4, modifier = Modifier.align(
            Alignment.CenterVertically))
    }
}
@Composable
fun Ability(ability: AbilitiesBusiness){
    Row {
        Checkbox(checked = !ability.isHidden, onCheckedChange = {  })
        NameLabel(name = ability.ability.name, style = MaterialTheme.typography.h4, modifier = Modifier.align(
            Alignment.CenterVertically))
    }
}

@Preview
@Composable
fun Abi(){
    PokemonTheme {
        Type(types = TypesBusiness(0, PokemonBusiness("Caca", "")))
    }
}
