package com.example.todoappmvvm

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_listFragment)
        },3000)

        val view =  inflater.inflate(R.layout.fragment_splash, container, false)

        val animTop = AnimationUtils.loadAnimation(view.context,R.anim.from_top)
        val animBotton = AnimationUtils.loadAnimation(view.context,R.anim.from_bottom)

        val tvSplash = view.findViewById<TextView>(R.id.tv_splash)
        val imgSplash = view.findViewById<ImageView>(R.id.img_octo)

        tvSplash.animation = animBotton
        imgSplash.animation = animTop


        return view
    }


}