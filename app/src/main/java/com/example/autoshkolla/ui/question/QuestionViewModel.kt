package com.example.autoshkolla.ui.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoshkolla.model.QuestionModel
import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.usecase.question.DeleteQuestionFromDb
import com.example.autoshkolla.usecase.question.GetQuestionFromDb
import com.example.autoshkolla.usecase.question.InsertQuestionToDb
import com.example.autoshkolla.usecase.rules.DeleteRulesFromDbUseCase
import com.example.autoshkolla.usecase.rules.GetRulesFromDBUseCase
import com.example.autoshkolla.usecase.rules.InsertRuleToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
private val getQuestionFromDb: GetQuestionFromDb,
private val insertQuestionToDb: InsertQuestionToDb,
private val deleteQuestionFromDb: DeleteQuestionFromDb
): ViewModel() {
    private var _getQuestionsFromDB: MutableLiveData<List<QuestionModel>> = MutableLiveData()
    val getQuestionsFromDB: LiveData<List<QuestionModel>> get() = _getQuestionsFromDB

    private var _insertQuestionToDB: MutableLiveData<Unit> = MutableLiveData()
    val insertQuestionToDB: LiveData<Unit> get() = _insertQuestionToDB

    private var _deleteQuestionFromDB: MutableLiveData<Unit> = MutableLiveData()
    val deleteQuestionFromDB: LiveData<Unit> get() = _deleteQuestionFromDB


    fun getQuestionsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            getQuestionFromDb.execute().collect {
                _getQuestionsFromDB.postValue(it)
            }
        }
    }

    fun insertQuestionToDB(questionModel: QuestionModel){
        viewModelScope.launch {
            _insertQuestionToDB.value = insertQuestionToDb.execute(questionModel)
        }
    }

    fun deleteQuestionFromDB(id:Int){
        viewModelScope.launch {
            _deleteQuestionFromDB.value = deleteQuestionFromDb.execute(id)
        }
    }
}