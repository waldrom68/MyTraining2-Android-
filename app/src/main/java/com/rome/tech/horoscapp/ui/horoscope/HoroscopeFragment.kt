package com.rome.tech.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rome.tech.horoscapp.databinding.FragmentHoroscopeBinding
import com.rome.tech.horoscapp.domain.model.HoroscopeInfo
import com.rome.tech.horoscapp.domain.model.HoroscopeModel
import com.rome.tech.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint // Aqui le indico a dagger que esta clase recibirá dependencias
class HoroscopeFragment : Fragment() {
    // creo una funcion delegada, por eso se usa by
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvHoroscope: RecyclerView
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initList()
        initUIState()
    }

    private fun initList() {
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    private fun initUIState() {
        // una corutina que depende del ciclo de vida de este fragment
        // aqui va a oir cuando cambie el MutableStateFlow del viewModel
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    private fun initListeners() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            // Identifico la clase del item seleccionado usando HoroscopeModel
            val type: HoroscopeModel =
                when (it) {
                    HoroscopeInfo.Aquarius -> HoroscopeModel.Aquarius
                    HoroscopeInfo.Aries -> HoroscopeModel.Aries
                    HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                    HoroscopeInfo.Capricorn -> HoroscopeModel.Capricorn
                    HoroscopeInfo.Gemini -> HoroscopeModel.Gemini
                    HoroscopeInfo.Leo -> HoroscopeModel.Leo
                    HoroscopeInfo.Libra -> HoroscopeModel.Libra
                    HoroscopeInfo.Pisces -> HoroscopeModel.Pisces
                    HoroscopeInfo.Sagittarius -> HoroscopeModel.Sagittarius
                    HoroscopeInfo.Scorpio -> HoroscopeModel.Scorpio
                    HoroscopeInfo.Taurus -> HoroscopeModel.Taurus
                    HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
                }


            // navego de acuerdo a lo configurado en res/navigation, comentado en HoroscopeDetailActivity
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )

//            var toast: Toast = Toast.makeText(this.context, getString(it.name), Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
        })
    }

    override fun onStart() {
        Log.i("SEMILLA", "HoroscopeFragment on onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i("SEMILLA", "HoroscopeFragment on onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i("SEMILLA", "HoroscopeFragment on onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("SEMILLA", "HoroscopeFragment on onStop")
        super.onStop()
    }

}
