package ir.vahidmohammadisan.mobisoft.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.vahidmohammadisan.mobisoft.data.repositories.Repository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}