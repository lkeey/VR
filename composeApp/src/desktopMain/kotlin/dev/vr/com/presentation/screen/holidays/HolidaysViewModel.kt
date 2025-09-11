package dev.vr.com.presentation.screen.holidays

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.data.model.CategoryModel
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HolidaysViewModel (
    repository: GameRepository
) : ViewModel() {

    private val getGamesUseCase = GetGamesUseCase(repository)

    private val _state = MutableStateFlow(HolidaysState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    init {
        loadHolidays()
    }

    private fun loadHolidays() {
        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            getGamesUseCase
                .invoke(CategoryModel.HOLIDAYS)
                .catch { e ->
                    _state.update {
                        it.copy(
                            error = e.message,
                            isLoading = false
                        )
                    }
                }
                .collect { games ->
                    _state.update {
                        it.copy(
                            holidays = games,
                            isLoading = false,
                            error = null
                        )
                    }
                }

        }
    }


}
