package com.main.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.main.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val totalCount = 6
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
        showNextQuiz()
onClick()



    }

    @SuppressLint("SetTextI18n")
    private fun showNextQuiz() {
         binding.count.text = getString(R.string.count_label, quizCount)//quizcount

        val quiz = quizData[0]//pick one quiz set
        val q= quiz[0]
        binding.question.text = "what is the capital of $q ?"
         rightAnswer = quiz[1]
        quiz.removeAt(0)//remove that quiz
         quiz.shuffle()//shuffle choice and answers
        binding.choice1.text =" 1.{$quiz[0]}"
        binding.choice2.text = "2.{$quiz[1]}"
        binding.choice3.text = "3.{$quiz[2]}"
        binding.choice4.text ="3.{$quiz[3]}"
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
     quizCount++
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
        quizCount++


    }





    private fun checkQuizCount() {
        if (quizCount == totalCount) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("right answer count", rightAnswerCount)
            startActivity(intent)

        } else {
            showNextQuiz()
        }

    }
    private fun onClick(){
        binding.choice1.setOnClickListener{
          val ans1 = binding.choice1.text.toString()
          if (ans1==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}

        }
        binding.choice2.setOnClickListener{
            val ans2=  binding.choice2.text.toString()
            if (ans2==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}
        }
        binding.choice3.setOnClickListener{
            val ans3=  binding.choice3.text.toString()
            if (ans3==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}
        }
        binding.choice4.setOnClickListener{
            val ans4=  binding.choice4.text.toString()
            if (ans4==rightAnswer){
                rightAnswer()
            }else{ wrongAnswer()}
        }
    }
}
