package com.example.todoappmvvm

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoappmvvm.databinding.FragmentSplashBinding


@Suppress("DEPRECATION")
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val splashScreen = 5000


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)





        val animasyon1 = AnimationUtils.loadAnimation(activity,R.anim.animasyon1)
        val animasyon2 = AnimationUtils.loadAnimation(activity,R.anim.animasyon2)
        val animasyon3 = AnimationUtils.loadAnimation(activity,R.anim.animasyon3)

        val imageView = binding.imageView
        val imageView2 = binding.imageView2
        val textView = binding.tvSplash

        imageView.animation = animasyon1
        imageView2.animation = animasyon2
        textView.animation = animasyon3

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_listFragment)
        },splashScreen.toLong())


        return binding.root



    }






    //app bar ve status bar gizleme
    override fun onResume() {
        super.onResume()
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.hide()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

    }

    override fun onStop() {
        super.onStop()
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }










    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}