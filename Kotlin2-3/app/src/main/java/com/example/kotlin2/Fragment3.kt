package com.example.kotlin2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlin2.databinding.Fragment3Binding
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Fragment3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(com.example.kotlin2.ARG_PARAM1)
            param2 = it.getString(com.example.kotlin2.ARG_PARAM2)
        }
    }

    lateinit var binding: Fragment3Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Создание экземпляра класса Fragment1Binding и связывание его с разметкой фрагмента
        binding = Fragment3Binding.inflate(inflater, container, false)
        binding.back2.setOnClickListener {
            // Навигация к другому фрагменту с помощью NavController и указанием ID действия
            findNavController().navigate(R.id.action_fragment3_to_fragment2)
        }
        // Возвращение корневого View разметки фрагмента
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment3().apply {
                arguments = Bundle().apply {
                    putString(com.example.kotlin2.ARG_PARAM1, param1)
                    putString(com.example.kotlin2.ARG_PARAM2, param2)
                }
            }
    }
}