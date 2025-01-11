package com.dev.thorugoh.randomnumbers

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.random.Random

@Serializable
data class UiState(
    val drawAmountNumber: Int = 2,
    val initialLimit: Int = 1,
    val finalLimit: Int = 100,
    val shouldRepeatNumbers: Boolean = false,
    val currentDrawNumber: Int = 0,
    val drawNumbers: List<Int> = emptyList()
)

class DrawViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun setDrawAmountNumber(number: Int) {
        _uiState.value = _uiState.value.copy(drawAmountNumber = number)
    }

    fun setInitialLimit(number: Int) {
        _uiState.value = _uiState.value.copy(initialLimit = number)
    }

    fun setFinalLimit(number: Int) {
        _uiState.value = _uiState.value.copy(finalLimit = number)
    }

    fun setShouldRepeatNumbers(shouldRepeat: Boolean) {
        _uiState.value = _uiState.value.copy(shouldRepeatNumbers = shouldRepeat)
    }

    fun drawNumbers() {
        val drawNumbers = mutableListOf<Int>()
        repeat(_uiState.value.drawAmountNumber) {
            var number = Random.nextInt(_uiState.value.initialLimit, _uiState.value.finalLimit)
            while (drawNumbers.contains(number) && !_uiState.value.shouldRepeatNumbers) {
                number = Random.nextInt(_uiState.value.initialLimit, _uiState.value.finalLimit)
            }
            drawNumbers.add(number)
        }

        val deserialized = Json { encodeDefaults = true } .encodeToString(_uiState.value)
        Log.d("DrawViewModel", "Ui state serialized $deserialized" )
        Log.d("DrawViewModel", "Ui state serialized ${Gson().toJson(_uiState.value) }" )

        Log.d("Serializable", Json.decodeFromString<UiState>(deserialized).toString())
        Log.d("GSON", Gson().fromJson(deserialized, UiState::class.java).toString())

        _uiState.value = _uiState.value.copy(
            currentDrawNumber = _uiState.value.currentDrawNumber + 1,
            drawNumbers = drawNumbers.toList()
        )
    }
}