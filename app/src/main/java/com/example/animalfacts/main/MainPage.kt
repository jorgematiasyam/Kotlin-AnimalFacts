package com.example.animalfacts.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animalfacts.ui.theme.AnimalFactsTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainPage(modifier: Modifier = Modifier) {
    var viewmodel : MainViewModel = viewModel()

   MainView(estado = viewmodel.estadoDeUI){ intencion ->
        viewmodel.ejecutar(intencion)

   }
}

@Composable
fun MainView(modifier: Modifier = Modifier, estado: MainEstado, ejecutar: (MainIntencion) -> Unit){
    Column(modifier = modifier.padding(20.dp)) {
        when (estado) {
            is MainEstado.Correcto -> MainViewCorrecto(estado)
            is MainEstado.Error -> MainViewError(estado)
            is MainEstado.Cargando -> MainViewCargando()

        }
            Button(onClick = { ejecutar(MainIntencion.Refrescar) }) {
                Text(text = "Refrescar")
            }
                Button (onClick = { ejecutar(MainIntencion.RomperTodo) }) {
                Text(text = "Romper todo")

        }
    }

    Text(
        modifier = modifier,
        text = "Hello",

    )
}

@Composable
fun MainViewCorrecto(estado : MainEstado.Correcto){
    Text(text = estado.mensaje,
        style = MaterialTheme.typography.labelMedium
    )

}

@Composable
fun MainViewCargando(){
    Text(text = "Cargando")
}

@Composable
fun MainViewError(estado : MainEstado.Error){
    Text(text = estado.error,
    style = MaterialTheme.typography.displayLarge
        )
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    AnimalFactsTheme {
        MainView(estado = MainEstado.Correcto("Correcto")){}
    }
}