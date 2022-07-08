package br.com.zup.marvel.ui.heroeadd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.R
import br.com.zup.marvel.databinding.FragmentHeroeAddBinding
import br.com.zup.marvel.domain.model.Director
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.ui.heroeadd.viewmodel.HeroeAddViewModel
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.viewstate.ViewState


class HeroeAddFragment : Fragment() {

    private lateinit var binding: FragmentHeroeAddBinding
    private val viewModel: HeroeAddViewModel by lazy {
        ViewModelProvider(this)[HeroeAddViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroeAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        binding.bvSaveHeroe.setOnClickListener {
            viewModel.insertHeroe(
                Marvel(
                    nome = binding.etNomeHeroe.text.toString(),
                    detalhe = Director(
                        info = binding.etMovieDirectorInfoAdd.text.toString()
                    )
                )
            )
        }
        initObserver()
    }
        private fun initObserver() {
            viewModel.heroeAddState.observe(this.viewLifecycleOwner) {
                when (it) {
                    is ViewState.Success -> {
                        Toast.makeText(context, "Filme cadastrado com sucesso!", Toast.LENGTH_LONG)
                            .show()
                    }
                    is ViewState.Error -> {
                        Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
}