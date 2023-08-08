package com.example.favouritegitrepo.ui.favouriterepo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.favouritegitrepo.data.remote.NetworkResult
import com.example.favouritegitrepo.databinding.FragmentAddRepoBinding


class AddRepoFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentAddRepoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setupObserver()

        binding.addRepo.setOnClickListener {
            val owner: String = binding.etOwner.text.toString()
            val repo: String = binding.etRepo.text.toString()

            // Check internet connectivity
            if (isInternetAvailable(requireContext())) {
                viewModel.getRepo(owner, repo)
            } else {
                showToast("No internet connection!")
            }
        }
    }

    private fun setupObserver() {
        viewModel.response.observe(viewLifecycleOwner) { respone ->

            Log.d("result", respone.message.toString())
            when (respone) {
                is NetworkResult.SUCCESS -> {
                    hideLoader()
                    Toast.makeText(requireContext(), "Repository Added", Toast.LENGTH_LONG).show()
                         close()
                }
                is NetworkResult.FAILURE -> {
                    hideLoader()
                    Toast.makeText(requireContext(), respone.message, Toast.LENGTH_LONG).show()
                }
                is NetworkResult.LOADING -> {
                    showLoader()
                }
                else -> {

                }
            }
        }

    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        if (network != null) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        }
        return false
    }


    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    private fun showLoader() {
        binding.progressCircular.visibility = View.VISIBLE

    }

    private fun close() {
        fragmentManager?.popBackStack()
    }

    private fun hideLoader() {
        binding.progressCircular.visibility = View.GONE
    }


}