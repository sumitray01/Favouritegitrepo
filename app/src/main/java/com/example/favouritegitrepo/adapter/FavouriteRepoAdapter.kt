package com.example.favouritegitrepo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favouritegitrepo.data.model.FavouriteRepo
import com.example.favouritegitrepo.databinding.ItemRepoBinding

class FavouriteRepoAdapter(val favouriteListener: FavouriteListener) : RecyclerView.Adapter<FavouriteRepoAdapter.RepoViewHolder>() {
    private var favouriteRepo: List<FavouriteRepo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favouriteRepo?.size ?: 0
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        favouriteRepo?.get(position)?.let { favouriteRepo ->
            holder.setData(favouriteRepo)
        }
    }

    inner class RepoViewHolder(private val itemRepoBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(itemRepoBinding.root) {
        fun setData(favouriteRepo: FavouriteRepo) {
            itemRepoBinding.tvTitle.text = favouriteRepo.name
            itemRepoBinding.tvDescription.text = favouriteRepo.description
            itemRepoBinding.shareIcon.setOnClickListener{
                favouriteListener.onShareClick(favouriteRepo)
            }

            itemRepoBinding.root.setOnClickListener{
                favouriteListener.onItemClick(favouriteRepo)
            }


        }

    }
    fun updateList(favouriteRepo: List<FavouriteRepo>) {
        this.favouriteRepo = favouriteRepo
        notifyDataSetChanged()
    }



}
interface FavouriteListener{
    fun onShareClick(favouriteRepo: FavouriteRepo)

    fun onItemClick(favouriteRepo: FavouriteRepo)
}

