package br.com.zup.marvel.ui.heroelist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.marvel.MOVIE_KEY
import br.com.zup.marvel.R
import br.com.zup.marvel.databinding.FragmentHeroeListBinding
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.ui.heroelist.viewmodel.HeroeListViewModel
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.viewstate.ViewState


class HeroeListFragment : Fragment() {

    private lateinit var binding: FragmentHeroeListBinding

    private val viewModel: HeroeListViewModel by lazy {
        ViewModelProvider(this)[HeroeListViewModel::class.java]
    }

    private val adapter: MarvelAdapter by lazy {
        MarvelAdapter(arrayListOf(), this::goToHeroeDetail)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.movie_title_menu)
        initObserver()
        setUpRvMovieList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllMovies()
    }

    private fun initObserver() {
        viewModel.movieListState.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    adapter.updateMovieList(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun setUpRvMovieList() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }

    private fun goToHeroeDetail(marvel: Marvel) {
        val bundle = bundleOf(MOVIE_KEY to marvel)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_heroeListFragment_to_heroeDetailFragment, bundle
        )
    }

}