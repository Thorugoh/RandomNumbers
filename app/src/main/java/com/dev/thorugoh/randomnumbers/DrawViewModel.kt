package com.dev.thorugoh.randomnumbers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

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

        _uiState.value = _uiState.value.copy(
            currentDrawNumber = _uiState.value.currentDrawNumber + 1,
            drawNumbers = drawNumbers.toList()
        )
    }
}