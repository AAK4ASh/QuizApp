package com.main.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.main.quizapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var  rightAnswer:String?=null
    private var rightAnswerCount=0
    private  var quizCount= 1
    private val QUIZ_COUNT=5
    private  val quizData = mutableListOf(

        mutableListOf("China", "Beijing", "Jakarta", "Manila", "Stockholm"),
        mutableListOf("India", "New Delhi", "Beijing", "Bangkok", "Seoul"),
        mutableListOf("Indonesia", "Jakarta", "Manila", "New Delhi", "Kuala Lumpur"),
        mutableListOf("Japan", "Tokyo", "Bangkok", "Taipei", "Jakarta"),
        mutableListOf("Thailand", "Bangkok", "Berlin", "Havana", "Kingston"),
        mutableListOf("Brazil", "Brasilia", "Havana", "Bangkok", "Copenhagen"),
        mutableListOf("Canada", "Ottawa", "Bern", "Copenhagen", "Jakarta"),
        mutableListOf("Cuba", "Havana", "Bern", "London", "Mexico City"),
        mutableListOf("Mexico", "Mexico City", "Ottawa", "Berlin", "Santiago"),
        mutableListOf("United States", "Washington D.C.", "San Jose", "Buenos Aires", "Kuala Lumpur"),
        mutableListOf("France", "Paris", "Ottawa", "Copenhagen", "Tokyo"),
        mutableListOf("Germany", "Berlin", "Copenhagen", "Bangkok", "Santiago"),
        mutableListOf("Italy", "Rome", "London", "Paris", "Athens"),
        mutableListOf("Spain", "Madrid", "Mexico City", "Jakarta", "Havana"),
        mutableListOf("United Kingdom", "London", "Rome", "Paris", "Singapore")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        quizData.shuffle()
        showNextQuiz()
    }
    private fun showNextQuiz(){
        binding.count.text=getString(R.string.count_label,quizCount)
val quiz = quizData[0]//pick one quiz set
        rightAnswer=quiz[1]

        quiz.shuffle()//shuffle choice and answers
        binding.question.text= quiz[0]
        binding.answer1.text=quiz[0]
        binding.answer2.text=quiz[1]
        binding.answer3.text=quiz[2]
        binding.answer4.text=quiz[3]
        quiz.removeAt(0)//remove country from quiz
    }
    fun checkAnswer(view:View){
        val answerButton:Button=findViewById(view.id)
        val btnText=answerButton.text.toString()
        val alertitle:String
        if (btnText==rightAnswer){
            alertitle="correct"
            rightAnswerCount++
        }else{
            alertitle="wrong"
        }
        AlertDialog.Builder(this)
            .setTitle(alertitle)
            .setMessage("Answer is $rightAnswer")
            .setPositiveButton("ok"){_,_->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()

    }
    fun checkQuizCount(){
        if (quizCount==QUIZ_COUNT){

        }
        else{
            quizCount++
            showNextQuiz()
        }

    }
}
