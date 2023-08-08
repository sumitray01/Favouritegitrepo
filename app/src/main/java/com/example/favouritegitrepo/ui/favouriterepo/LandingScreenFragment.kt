package com.example.favouritegitrepo.ui.favouriterepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favouritegitrepo.R
import com.example.favouritegitrepo.adapter.FavouriteListener
import com.example.favouritegitrepo.adapter.FavouriteRepoAdapter
import com.example.favouritegitrepo.data.model.FavouriteRepo
import com.example.favouritegitrepo.databinding.FragmentLandingScreenBinding
import com.example.favouritegitrepo.util.openLinkInChrome
import com.example.favouritegitrepo.util.shareUrl


class LandingScreenFragment : Fragment(), FavouriteListener {

    private lateinit var adapter: FavouriteRepoAdapter
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentLandingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        binding.floatingActionButton.setOnClickListener {
            openAddFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllReposFromDb()
    }

    private fun openAddFragment() {
        val addFragment = AddRepoFragment()
        activity?.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, addFragment)
                .addToBackStack("AddFragment")
                .commit()
        }

    }

    private fun initUi() {
        setupRecyclerView()
        viewModel.repoList.observe(requireActivity()) { repoList ->
            if (repoList.isNullOrEmpty()) {
                binding.label.visibility = View.VISIBLE
                binding.rvMovieList.visibility = View.GONE

            } else {
                binding.rvMovieList.visibility = View.VISIBLE
                binding.label.visibility = View.GONE
                upDateRecyclerView(repoList)
            }
        }


    }




    private fun setupRecyclerView() {
        adapter = FavouriteRepoAdapter(this)
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun upDateRecyclerView(repoList: List<FavouriteRepo>) {
        adapter.updateList(repoList)

    }

    override fun onShareClick(favouriteRepo: FavouriteRepo) {
        favouriteRepo.html_url?.let {
            shareUrl(requireActivity(), it)
        } ?: Toast.makeText(context, "Url not found !", Toast.LENGTH_SHORT).show()

    }

    override fun onItemClick(favouriteRepo: FavouriteRepo) {
        favouriteRepo.html_url?.let {
            openLinkInChrome(requireActivity(), it)
        } ?: Toast.makeText(context, "Url not found !", Toast.LENGTH_SHORT).show()
    }

}