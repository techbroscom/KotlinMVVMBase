package com.example.kotlinmvvmbase.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinmvvmbase.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getInt("userId") ?: 0
        val userName = arguments?.getString("userName") ?: ""
        val userEmail = arguments?.getString("userEmail") ?: ""

        binding.tvDetailId.text = "ID: $userId"
        binding.tvDetailName.text = "Name: $userName"
        binding.tvDetailEmail.text = "Email: $userEmail"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}