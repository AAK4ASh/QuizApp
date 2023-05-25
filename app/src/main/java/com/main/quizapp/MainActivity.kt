package com.main.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.main.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val totalCount = 5
    private val quizData = mutableListOf(

        mutableListOf("China", "Beijing", "Jakarta", "Manila", "Stockholm"),
        mutableListOf("India", "New Delhi", "Beijing", "Bangkok", "Seoul"),
        mutableListOf("Indonesia", "Jakarta", "Manila", "New Delhi", "Kuala Lumpur"),
        mutableListOf("Japan", "Tokyo", "Bangkok", "Taipei", "Jakarta"),
        mutableListOf("Thailand", "Bangkok", "Berlin", "Havana", "Kingston"),
        mutableListOf("Brazil", "Brasilia", "Havana", "Bangkok", "Copenhagen"),
        mutableListOf("Canada", "Ottawa", "Bern", "Copenhagen", "Jakarta"),
        mutableListOf("Cuba", "Havana", "Bern", "London", "Mexico City"),
        mutableListOf("Mexico", "Mexico City", "Ottawa", "Berlin", "Santiago"),
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
onClick()
        showNextQuiz()


    }

    private fun showNextQuiz() {
         binding.count.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]//pick one quiz set
        val q= quiz[0]
        binding.question.text = "what is the capital of $q ?"
         rightAnswer = quiz[1]
        quiz.removeAt(0)
         quiz.shuffle()//shuffle choice and answers
        binding.answer1.text = quiz[0]
        binding.answer2.text = quiz[1]
        binding.answer3.text = quiz[2]
        binding.answer4.text = quiz[3]
    quizData.removeAt(0)//remove country from quiz
     onClick()

    }
 private fun rightAnswer() {

     AlertDialog.Builder(this)
         .setTitle("Correct")
         .setPositiveButton("ok") { _, _ ->
showNextQuiz()
             checkQuizCount()
         }
         .setCancelable(false)
         .show()
     rightAnswerCount++
 }
    private fun wrongAnswer(){



            AlertDialog.Builder(this)
                .setTitle("Wrong")
                .setMessage(" The answer is $rightAnswer")
                .setPositiveButton("ok") { _, _ ->
                    showNextQuiz()

                    checkQuizCount()
                }
                .setCancelable(false)
                .show()
    }





    private fun checkQuizCount() {
        if (quizCount == totalCount) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("right answer count", rightAnswerCount)
            startActivity(intent)

        } else {
            showNextQuiz()
            quizCount++


        }

    }
    private fun onClick(){
        binding.answer1.setOnClickListener{
          val ans1 = binding.answer1.text.toString()
          if (ans1==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}

        }
        binding.answer2.setOnClickListener{
            val ans2=  binding.answer2.text.toString()
            if (ans2==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}
        }
        binding.answer3.setOnClickListener{
            val ans3=  binding.answer3.text.toString()
            if (ans3==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}
        }
        binding.answer4.setOnClickListener{
            val ans4=  binding.answer4.text.toString()
            if (ans4==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}
        }
    }
}
