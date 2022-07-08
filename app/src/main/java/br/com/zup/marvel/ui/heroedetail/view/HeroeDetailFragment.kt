package br.com.zup.marvel.ui.heroedetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.marvel.MOVIE_KEY
import br.com.zup.marvel.R
import br.com.zup.marvel.databinding.FragmentHeroeAddBinding
import br.com.zup.marvel.databinding.FragmentHeroeDetailBinding
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.ui.home.view.HomeActivity


class HeroeDetailFragment : Fragment() {

    private lateinit var binding: FragmentHeroeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPassedData()
    }
    private fun getPassedData() {
        val movie = arguments?.getParcelable<Marvel>(MOVIE_KEY)
        movie?.let {
            binding.imageView.setImageResource(it.image)
            binding.tvMovieTitle.text = it.nome
            binding.tvDirectorName.text = it.detalhe.info
            (activity as HomeActivity).supportActionBar?.title = it.nome
        }
    }

}