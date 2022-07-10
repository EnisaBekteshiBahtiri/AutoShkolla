package com.example.autoshkolla.ui.home.categoryA.signs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.model.SignModel
import com.example.autoshkolla.usecase.rules.DeleteRulesFromDbUseCase
import com.example.autoshkolla.usecase.rules.GetRulesFromDBUseCase
import com.example.autoshkolla.usecase.rules.InsertRuleToDbUseCase
import com.example.autoshkolla.usecase.signs.DeleteSignFromDbUseCase
import com.example.autoshkolla.usecase.signs.GetSignsFromDbUseCase
import com.example.autoshkolla.usecase.signs.InsertSignToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CategoryASignsViewModel @Inject constructor(
    private val getSignsFromDbUseCase: GetSignsFromDbUseCase,
    private val insertSignToDbUseCase: InsertSignToDbUseCase,
    private val deleteSignFromDbUseCase: DeleteSignFromDbUseCase
): ViewModel() {
    private var _getSignsFromDB: MutableLiveData<List<SignModel>> = MutableLiveData()
    val getSignsFromDB: LiveData<List<SignModel>> get() = _getSignsFromDB

    private var _insertSignToDB: MutableLiveData<Unit> = MutableLiveData()
    val insertSignToDB: LiveData<Unit> get() = _insertSignToDB

    private var _deleteSignFromDB: MutableLiveData<Unit> = MutableLiveData()
    val deleteSignFromDB: LiveData<Unit> get() = _deleteSignFromDB


    fun getSignsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            getSignsFromDbUseCase.execute().collect {
                _getSignsFromDB.postValue(it)
            }
        }
    }

    fun insertSignToDB(signModel: SignModel){
        viewModelScope.launch {
            _insertSignToDB.value = insertSignToDbUseCase.execute(signModel)
        }
    }

    fun deleteSignFromDB(id:Int){
        viewModelScope.launch {
            _deleteSignFromDB.value = deleteSignFromDbUseCase.execute(id)
        }
    }
}