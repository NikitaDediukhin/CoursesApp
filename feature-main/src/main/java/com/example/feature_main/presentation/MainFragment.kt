package com.example.feature_main.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.feature_main.di.MainComponentViewModel
import com.example.feature_main.presentation.adapter.CoursesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = requireNotNull(_binding) {
            "Binding is not initialized or already cleared"
        }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel
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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        observeState()

        binding.sortButton.setOnClickListener {
            viewModel.onSortClick()
        }
    }

    private fun setupRecyclerView() {
        coursesAdapter = CoursesAdapter(
            onFavoriteClick = { course ->
                viewModel.onFavoriteClick(course)
            }
        )

        binding.coursesRecyclerView.apply {
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                binding.errorTextView.visibility = if (state.error != null) View.VISIBLE else View.GONE
                binding.errorTextView.text = state.error.orEmpty()

                coursesAdapter.submitList(state.courses)
            }
        }
    }

    override fun onDestroyView() {
        binding.coursesRecyclerView.adapter = null
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}