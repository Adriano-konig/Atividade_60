package br.com.zup.marvel.ui.heroelist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvel.databinding.HeroeItemBinding
import br.com.zup.marvel.domain.model.Marvel

class MarvelAdapter(
    private var heroeList: MutableList<Marvel>,
    private val clickMarvel: (marvel: Marvel) -> Unit
) : RecyclerView.Adapter<MarvelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HeroeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = heroeList[position]
        holder.showMovieInfo(movie)
        holder.binding.cvItemLista.setOnClickListener {
            clickMarvel(movie)
        }
        holder.showMovieInfo(movie)
    }

    override fun getItemCount() = heroeList.size

    fun updateMovieList(newList: List<Marvel>) {
        if (heroeList.size == 0) {
            heroeList = newList as MutableList<Marvel>
        } else {
            heroeList.addAll(newList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: HeroeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showMovieInfo(movie: Marvel) {
            binding.tvMovieName.text = movie.nome
            binding.ivMoviePoster.setImageResource(movie.image)
        }
    }
}