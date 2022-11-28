package com.example.videoplayer.presentation

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.data.adapter.VideosAdapter
import com.example.videoplayer.data.network.ApiState
import com.example.videoplayer.databinding.FragmentVideoBinding
import com.example.videoplayer.domain.models.ModelDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class VideoFragment : Fragment(), VideosAdapter.ClickListener {
    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private val videosViewModel: VideoViewModel by viewModels()
    private val videosAdapter by lazy { VideosAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycle.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycle.adapter = videosAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                videosViewModel.videoStateFlow.collect { state ->
                    when (state) {
                        is ApiState.Success -> {
                            videosAdapter.submitList(state.data.toMutableList())
                            binding.videoView.setVideoPath(videosViewModel.videoPath)
                            binding.videoView.start()
                            binding.videoView.setOnPreparedListener {
                                it.isLooping = true
                            }
                        }
                        is ApiState.Failure -> {
                            Log.d("Error", "The error is ${state.message}")
                        }
                        is ApiState.Empty -> {}
                    }
                }
            }
        }
        binding.btnBack.setOnClickListener {
            requireActivity().finish() //Завершаем приложение
        }
        binding.btnText.setOnClickListener {
            binding.editText.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = VideoFragment()
    }

    override fun onItemCLick(modelDTO: ModelDTO) {
        videosViewModel.videoPath = modelDTO.fileUrl ?: ""
        activity?.runOnUiThread {
            with(binding) {
                videoView.setVideoPath(videosViewModel.videoPath)
                videoView.start()
            }
        }
    }
}
