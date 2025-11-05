package com.example.kotlinmvvmbase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: HomeViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        setupRecyclerView()
        observeData()

        vm.fetchUsers()
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter { user ->
            val bundle = Bundle().apply {
                putInt("userId", user.id)
                putString("userName", user.name)
                putString("userEmail", user.email)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.adapter = adapter
    }

    private fun observeData() {
        vm.users.observe(viewLifecycleOwner) { userList ->
            adapter.submitList(userList)
            binding.progressBar.visibility = View.GONE
        }

        vm.error.observe(viewLifecycleOwner) { errorMsg ->
            binding.progressBar.visibility = View.GONE
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
        }

        vm.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}