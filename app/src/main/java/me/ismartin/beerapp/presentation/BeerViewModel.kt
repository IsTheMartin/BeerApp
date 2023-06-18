package me.ismartin.beerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import me.ismartin.beerapp.data.local.BeerEntity
import me.ismartin.beerapp.data.mappers.toBeer

@HiltViewModel
class BeerViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>
): ViewModel() {

    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toBeer() }
        }
        .cachedIn(viewModelScope)

}