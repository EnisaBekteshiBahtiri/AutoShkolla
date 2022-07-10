package com.example.autoshkolla.ui.question

import android.content.ContentValues
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.autoshkolla.R
import com.example.autoshkolla.databinding.QuestionFragmentBinding
import com.example.autoshkolla.model.QuestionModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.question_fragment.*

@AndroidEntryPoint
class QuestionFragment() : Fragment() {

    private val viewModel by viewModels<QuestionViewModel>()
    private lateinit var binding: QuestionFragmentBinding

    private var score:Int=0

    private var currentPosition:Int=1
    private lateinit var questionList: List<QuestionModel>
    private var selecedOption:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QuestionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertData()
        getData()

        observeData()
        //questionList=setData.getQuestion()

     //   setQuestion()

        opt_1.setOnClickListener{

            selectedOptionStyle(opt_1,1)
        }
        opt_2.setOnClickListener{

            selectedOptionStyle(opt_2,2)
        }
        opt_3.setOnClickListener{

            selectedOptionStyle(opt_3,3)
        }
        opt_4.setOnClickListener{

            selectedOptionStyle(opt_4,4)
        }

        submit.setOnClickListener {
            if(selecedOption!=0)
            {
                val question=questionList!![currentPosition-1]
                if(selecedOption!=question.correctAnswer)
                {
                    setColor(selecedOption, R.drawable.wrong_question_option)
                }else{
                    score++;
                }
                setColor(question.correctAnswer,R.drawable.correct_question_option)
                if(currentPosition==questionList!!.size)
                    submit.text="FINISH"
                else
                    submit.text="Go to Next"
            }else{
                currentPosition++
                when{
                    currentPosition<=questionList!!.size->{
                        setQuestion()
                    }
                    else->{

                        val directions = QuestionFragmentDirections.actionQuestionFragmentToResultFragment()
                        findNavController().navigate(directions)
                    }
                }
            }
            selecedOption=0
        }

    }

    private fun setColor(opt:Int, color:Int){
        when(opt){
            1->{
                opt_1.background= ContextCompat.getDrawable(requireContext(),color)
            }
            2->{
                opt_2.background= ContextCompat.getDrawable(requireContext(),color)
            }
            3->{
                opt_3.background= ContextCompat.getDrawable(requireContext(),color)
            }
            4->{
                opt_4.background= ContextCompat.getDrawable(requireContext(),color)
            }
        }
    }


    private fun setQuestion(){

        val question = questionList[currentPosition-1]
        setOptionStyle()


        progress_bar.progress=currentPosition
        progress_bar.max=questionList!!.size
        progress_text.text="${currentPosition}"+"/"+"${questionList!!.size}"
        question_text.text=question.question
        opt_1.text=question.optionOne
        opt_2.text=question.optionTwo
        opt_3.text=question.optionThree
        opt_4.text=question.optionFour

    }

    private fun setOptionStyle(){
        var optionList:ArrayList<TextView> = arrayListOf()
        optionList.add(0,opt_1)
        optionList.add(1,opt_2)
        optionList.add(2,opt_3)
        optionList.add(3,opt_4)

        for(op in optionList)
        {
            op.setTextColor(Color.parseColor("#555151"))
            op.background= ContextCompat.getDrawable(requireContext(),R.drawable.question_option)
            op.typeface= Typeface.DEFAULT
        }
    }

    private fun selectedOptionStyle(view: TextView, opt:Int){
        setOptionStyle()
        selecedOption=opt
        view.background= ContextCompat.getDrawable(requireContext(),R.drawable.selected_question_option)
        view.typeface= Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }

    private fun observeData(){
        viewModel.getQuestionsFromDB.observe(viewLifecycleOwner) {
            if(it.isNullOrEmpty()){

            }
            if(!it.isNullOrEmpty()){
               questionList = it
                setQuestion()
                Log.d(ContentValues.TAG, "DATAAAAA: $it")
            }
        }

        viewModel.deleteQuestionFromDB.observe(viewLifecycleOwner){
            //   viewModel.deleteRuleFromDB(2)
        }

    }

    private fun insertData() {
        viewModel.insertQuestionToDB(
            QuestionModel(1,
                "What is the first thing you should do during an enforcement stop ?",

                "Stop on the center median",
                "Turn on your right turn signal",
                "Turn on your left turn signal ",
                "Stop on the left shoulder",
                2))

        viewModel.insertQuestionToDB(
            QuestionModel(2,
                "On a two-lane undivided highway, the maximum speed limit for vehicles towing trailers is  ?",

                "100km/h",
                "80km/h",
                "120km/h",
                "30km/h",
                2))

        viewModel.insertQuestionToDB(QuestionModel(
            3,
            "Special 'turnout' areas marked on a two-lane road",

            "allow drivers to stop and talk on their cell phones",
            "allow vehicles to make U-turns",
            "allow vehicles to make left turns",
            "allow vehicles to pass",
            4
        ))

        viewModel.insertQuestionToDB(QuestionModel(
            4,
            "A traffic signal with a flashing yellow arrow means that you should",

            "proceed with caution",
            "stop and be prepared to obey the next signal",
            "proceed with caution but turns are not permitted in the direction of the arrow",
            "proceed at increased speed as directed by the arrow",
            1
        ))

        viewModel.insertQuestionToDB(QuestionModel(
            5,
            "When two vehicles meet on a step mountain road where neither can pass, which vehicle has the right-of-way ?",

            "The vehicle traveling downhill",
            "The vehicle that come first",
            "The vehicle traveling uphill",
            "Both vehicles",
            3
        ))
        viewModel.insertQuestionToDB(QuestionModel(
            6,
            "A driver in front of you is signaling with his or her hand and arm pointing upward. This driver wants to?",

            "stop",
            "turn left",
            "slow down",
            "turn right",
            4
        ))
        viewModel.insertQuestionToDB(QuestionModel(
            7,
            "If you parked car rolls away and hits another vehicle, you should?",

            "get the other vehicle repaired",
            "sound your horn to attract attention ",
            "remove your car and go on your way ",
            "report the incident to the police ",
            4
        ))
        viewModel.insertQuestionToDB(QuestionModel(
            8,
            "At an unmarked crosswalk, you must",

            "signal your intentions and proceed",
            "yield the right-of-way to the pedestrians",
            "stop and ask the pedestrians to cross quickly ",
            "increase your speed and cross before the pedestrians",
            2
        ))
        viewModel.insertQuestionToDB(QuestionModel(
            9,
            "A driver who is a minor receives a phone call on his or her cell phone. He or she should",

            "not be carrying a cell phone while driving",
            "not answer the call",
            "answer the call in an emergency ",
            "use a hands-free cell phone to answer the call",
            2
        ))
        viewModel.insertQuestionToDB(QuestionModel(
            10,
            "A driver under an age of 21",

            "must have a blood alcohol content of zero",
            "can have a blood alcohol content of 0.02%",
            "can have a blood alcohol content of 0.08%",
            "can have a blood alcohol content of 0.04%",
            4
        ))

    }


    private fun getData() {
        viewModel.getQuestionsFromDB()

    }
}