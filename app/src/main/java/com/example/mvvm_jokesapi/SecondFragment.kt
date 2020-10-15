package com.example.mvvm_jokesapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.mvvm_jokesapi.databinding.FragmentSecondBinding
import com.example.mvvm_jokesapi.viewmodel.JokesDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel



/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    val viewModel by viewModel<JokesDetailViewModel>()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding= FragmentSecondBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        //read data from bundle
        val jokeProperty = SecondFragmentArgs.fromBundle(requireArguments()).selectedProperty
        binding.viewModel=viewModel
        viewModel.setProperty(jokeProperty)

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)
        return binding.root
    }


}