package com.example.animalfacts.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var estadoDeUI by mutableStateOf<MainEstado>(MainEstado.Correcto)
    fun ejecutar(intencion: MainIntencion) {
        when (intencion) {
            MainIntencion.Refrescar -> refrescar()
            MainIntencion.RomperTodo -> romperTodo()
        }
    }

    private fun refrescar() {
        estadoDeUI = MainEstado.Cargando
        viewModelScope.launch {
            pegarleAlServerDeMentiras()
        }

    }

    private suspend fun pegarleAlServerDeMentiras() {
        delay(2000)
        estadoDeUI = MainEstado.Correcto

    }

    private fun romperTodo() {
        estadoDeUI = MainEstado.Error

    }


}


