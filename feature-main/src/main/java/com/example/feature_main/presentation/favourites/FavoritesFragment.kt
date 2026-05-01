package com.example.feature_main.presentation.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_main.databinding.FragmentFavoritesBinding
import com.example.feature_main.di.MainComponentViewModel
import com.example.feature_main.presentation.adapter.CoursesAdapter
import com.example.feature_main.presentation.main.MainViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding
        get() = requireNotNull(_binding) {
            "Binding is not initialized or already cleared"
        }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var coursesAdapter: CoursesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this)[MainComponentViewModel::class.java]
            .mainComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coursesAdapter = CoursesAdapter(
            onFavoriteClick = { course ->
                viewModel.onFavoriteClick(course)
            }
        )

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecyclerView.adapter = coursesAdapter

        viewModel = ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collect { favorites ->
                binding.emptyTextView.visibility =
                    if (favorites.isEmpty()) View.VISIBLE else View.GONE

                coursesAdapter.submitList(favorites)
            }
        }
    }

    override fun onDestroyView() {
        binding.favoritesRecyclerView.adapter = null
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): FavoritesFragment = FavoritesFragment()
    }
}