package com.begobang.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.begobang.domain.business.VersionGroupDetailsBusiness
import java.util.Locale

@Composable
fun Moves(versionGroupDetails: List<VersionGroupDetailsBusiness>, move: String){
    Column(modifier = Modifier.padding(all = 20.dp)) {
        Card(elevation = 4.dp) {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                NameLabel(name = move, modifier = Modifier.padding(all = 12.dp))
                versionGroupDetails.forEach{
                    Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                        Text(text = it.moveLearnMethod.name.replace("-", " ")
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }, textAlign = TextAlign.Center, style = MaterialTheme.typography.body2)
                        Icon(imageVector = Icons.Default.ArrowCircleRight, contentDescription = "", modifier = Modifier.padding(horizontal = 10.dp))
                        Text(text = it.versionGroup.name.replace("-", " ")
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }, textAlign = TextAlign.Center, style = MaterialTheme.typography.body2,)
                    }
                }
            }

        }

        
    }
}
@Composable
@Preview
fun MovesPre(){
    Column {
        Row {
            Text(text = "Razor wind", textAlign = TextAlign.Center)
            Icon(imageVector = Icons.Default.ArrowCircleRight, contentDescription = "")
            Text(text = "Red Blue", textAlign = TextAlign.Center)
        }
        
    }
}