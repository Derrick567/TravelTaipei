package com.example.traveltaipei

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.fragment
import com.example.traveltaipei.home.presentation.HomeFragment

class MainActivity: AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (savedInstanceState == null) {
//            val bundle = bundleOf("some_int" to 0)
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<HomeFragment>(R.id.fragment_container_view, args = bundle)
//            }
//        }
    }
}