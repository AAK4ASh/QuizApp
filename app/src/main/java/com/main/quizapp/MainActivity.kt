package com.main.quizapp

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
showNextQuiz()
      onClick()

    }

    private fun showNextQuiz() {
        binding.count.text = getString(R.string.count_label, quizCount)
        val quiz = quizData[0]//pick one quiz set
        rightAnswer = quiz[1]
        val q= quiz[0]
        quiz.shuffle()//shuffle choice and answers
        binding.question.text = "what is the capital of $q ?"
        binding.answer1.text = quiz[0]
        binding.answer2.text = quiz[1]
        binding.answer3.text = quiz[2]
        binding.answer4.text = quiz[3]
    quizData.removeAt(0)//remove country from quiz

    }
 private fun checkAnswer() {

        val btnText:String = binding.answer1.text.toString()
        if (btnText == rightAnswer) {
            AlertDialog.Builder(this)
                .setTitle("Correct")
                .setPositiveButton("ok") { _, _ ->

                    checkQuizCount()
                }
                .setCancelable(false)
                .show()
            rightAnswerCount++
        } else {
            AlertDialog.Builder(this)
                .setTitle("Wrong")
                .setMessage(" The answer is $rightAnswer")
                .setPositiveButton("ok") { _, _ ->

                    checkQuizCount()
                }
                .setCancelable(false)
                .show()
        }


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
            checkAnswer()
        }
        binding.answer2.setOnClickListener{
            checkAnswer()
        }
        binding.answer3.setOnClickListener{
            checkAnswer()
        }
        binding.answer4.setOnClickListener{
            checkAnswer()
        }
    }
}
