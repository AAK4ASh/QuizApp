package com.main.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.main.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val score = intent.getIntExtra("right answer count",0)
        binding.textView2.text=getString(R.string.d_5,score)
        binding.button.
                setOnClickListener{
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
    }
}