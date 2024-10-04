package com.example.kotlin2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlin2.databinding.Fragment1Binding


class Fragment1 : Fragment() {
    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!
    private val loginViewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val username = binding.editTextText.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            loginViewModel.login(username, password)
            Log.d("Fragment1", "Login attempt: Username=$username, Password=$password")
        }

        loginViewModel.loginSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                findNavController().navigate(R.id.action_fragment1_to_fragment2)
                Log.d("Fragment1", "Navigating to Fragment2")
            } else {
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                Log.d("Fragment1", "Login failed")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

