package com.example.autoshkolla.ui.home.categoryC.rules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.usecase.rules.DeleteRulesFromDbUseCase
import com.example.autoshkolla.usecase.rules.GetRulesFromDBUseCase
import com.example.autoshkolla.usecase.rules.InsertRuleToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryCRulesViewModel @Inject constructor(
    private val getRulesFromDBUseCase: GetRulesFromDBUseCase,
    private val insertRulesDataUseCase: InsertRuleToDbUseCase,
    private val deleteRulesDataUseCase: DeleteRulesFromDbUseCase
): ViewModel() {
    private var _getRulesFromDB: MutableLiveData<List<RuleModel>> = MutableLiveData()
    val getRulesFromDB: LiveData<List<RuleModel>> get() = _getRulesFromDB

    private var _insertRuleToDB: MutableLiveData<Unit> = MutableLiveData()
    val insertRuleToDB: LiveData<Unit> get() = _insertRuleToDB

    private var _deleteRuleFromDB: MutableLiveData<Unit> = MutableLiveData()
    val deleteRuleFromDB: LiveData<Unit> get() = _deleteRuleFromDB


    fun getRulesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            getRulesFromDBUseCase.execute().collect {
                _getRulesFromDB.postValue(it)
            }
        }
    }

    fun insertRuleToDB(ruleModel: RuleModel){
        viewModelScope.launch {
            _insertRuleToDB.value = insertRulesDataUseCase.execute(ruleModel)
        }
    }

    fun deleteRuleFromDB(id:Int){
        viewModelScope.launch {
            _deleteRuleFromDB.value = deleteRulesDataUseCase.execute(id)
        }
    }
}