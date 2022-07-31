package com.example.typeconverters.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.typeconverters.databinding.FragmentHomeBinding
import com.example.typeconverters.model.User
import com.example.typeconverters.viewModel.SharedViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.submitBtn.setOnClickListener {
            val name = binding.name.text.toString()
            val id = binding.id.text.toString()
            val time = Date()
            if(name.isEmpty() || id.isEmpty()) {
                Toast.makeText(this.context,"Please Enter All Information",Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(User(id, name, time))
                binding.name.text.clear()
                binding.id.text.clear()
                Toast.makeText(this.context,"Data Added Successfully",Toast.LENGTH_SHORT).show()
            }
        }
        binding.searchBtn.setOnClickListener {
            val id = binding.search.text.toString()
            if(id.isEmpty()) {
                Toast.makeText(this.context,"Please Insert ID For search",Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val user = viewModel.search(id)
                    if(user == null) {
                        binding.nameView.text = "This ID Not Found"
                        binding.nameView.visibility = View.VISIBLE

                    } else {
                        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                        binding.apply {
                            nameView.text = user.name
                            nameView.visibility = View.VISIBLE
                            idView.text = user.uId
                            idView.visibility = View.VISIBLE
                            dateView.text = sdf.format(user.date)
                            dateView.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        return binding.root
    }
}