package com.example.kotlin2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kotlin2.databinding.Fragment2Binding

class Fragment2 : Fragment() {
    private lateinit var binding: Fragment2Binding
    private val loginViewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment2Binding.inflate(inflater, container, false)
        binding.imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment1)
        }

        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.textView.text = "Username: ${user.username}"
                binding.textView4.text = "Password: ${user.password}"
                Log.d("Fragment2", "User data received: Username=${user.username}, Password=${user.password}")
            }
        })

        return binding.root
    }
}

