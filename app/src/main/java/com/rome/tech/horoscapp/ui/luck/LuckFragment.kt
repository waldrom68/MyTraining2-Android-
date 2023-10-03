package com.rome.tech.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.rome.tech.horoscapp.R
import com.rome.tech.horoscapp.databinding.FragmentLuckBinding
import com.rome.tech.horoscapp.ui.core.listeners.OnSwipeTouchListener
import com.rome.tech.horoscapp.ui.model.LuckyModel
import com.rome.tech.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint // Aqui le indico a dagger que esta clase recibirá dependencias
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    private fun initUI() {
        preparePredictionCard()
        initListeners()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
//        binding.ivRoulette.setOnClickListener { spinRoulette() }
        binding.ivRoulette.setOnTouchListener(
        object : OnSwipeTouchListener(requireContext()) {
            val random = Random()
            var degree = 360

            override fun onSwipeRight() {
                degree = random.nextInt(1440) + 360
                spinRoulette(degree)
            }

            override fun onSwipeLeft() {
                degree = random.nextInt(1440)*(-1) - 360
                spinRoulette(degree)
            }

        })
    }

    private fun spinRoulette(degree:Int) {
//        val random = Random()
//        val degree = random.nextInt(1440) + 360

        val animator: ObjectAnimator =
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degree.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()

    }

    private fun slideCard() {
        // se utilizará una animacion creada en xml
        val slideUpAnimation: Animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.ivSmallCard.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
        binding.ivSmallCard.startAnimation(slideUpAnimation)

    }

    private fun growCard() {
        // se utilizará una animacion creada en xml
        val growAnimation: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.ivSmallCard.isVisible = false
                showPremonitionView()

            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
        binding.ivSmallCard.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 400

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.layoutPreview.isVisible = false
                binding.layoutPrediction.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        binding.layoutPreview.startAnimation(disappearAnimation)
        binding.layoutPrediction.startAnimation(appearAnimation)


    }

    private fun preparePredictionCard() {
        val luck: LuckyModel? = randomCardProvider.getLucky()
        luck?.let {
            val currentPrediction: String = getString(it.text)
            binding.tvLucky.text = currentPrediction
            binding.ivLuckyCard.setImageResource(it.image)

            binding.tvShare.setOnClickListener {
                shareResult(currentPrediction)
            }
        }
    }

    private fun shareResult(prediction: String) {
        // un Intent de camara no requiere autorizacion, por ejemplo, porque está fuera de nuestra app

        val sendIntent: Intent = Intent().apply {
//        action = Intent.ACTION_SCREEN_OFF
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}