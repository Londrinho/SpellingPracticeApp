package com.jimmy.spellingapp

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jimmy.spellingapp.databinding.FragmentSlyBinding


class SlyFragment : Fragment() {

    private lateinit var binding: FragmentSlyBinding
     private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sly, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playButton.setOnClickListener {
                if(!this::mediaPlayer.isInitialized){
                    mediaPlayer = MediaPlayer.create(requireContext(), R.raw.slyfile)
                }
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                mediaPlayer.seekTo(0)
                return@setOnClickListener
            }
            mediaPlayer.start()

            }

        binding.guessButton.setOnClickListener {checkIfWordIsCorrect()}


    }

    fun checkIfWordIsCorrect(){
        val correctWord = "sly"
       var guessedWord = binding.guessWordField.text.toString()

        if(guessedWord.equals(correctWord)){
            Toast.makeText(getActivity(), "Well done!",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(getActivity(),"Incorrect, try again please", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        if(this::mediaPlayer.isInitialized){
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        super.onDestroy()
    }
}