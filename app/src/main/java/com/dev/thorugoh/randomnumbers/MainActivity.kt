package com.dev.thorugoh.randomnumbers

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.dev.thorugoh.randomnumbers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvContent) as? NavHostFragment
        navHostFragment?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        with(binding) {
            btnDraw.setOnClickListener {
                // TODO: Navigate to next screen or go back (draw again)
                when(btnDraw.text) {
                    getString(R.string.draw) -> {
                        navController?.navigate(R.id.action_drawConfigFragment2_to_drawResultFragment2)
                        btnDraw.apply {
                            text = getString(R.string.draw_again)
                            setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_new_random), null)
                        }
                    }
                    getString(R.string.draw_again) -> {
                        navController?.popBackStack()
                        btnDraw.apply {
                            text = getString(R.string.draw)
                            setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_go), null)
                        }
                    }
                }
            }
        }


    }
}