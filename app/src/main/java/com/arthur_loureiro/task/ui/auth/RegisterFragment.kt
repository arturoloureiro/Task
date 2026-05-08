package com.arthur_loureiro.task.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arthur_loureiro.task.R
import com.arthur_loureiro.task.databinding.FragmentRegisterBinding
import com.arthur_loureiro.task.util.initToolbar
import com.arthur_loureiro.task.util.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import androidx.core.view.isVisible

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }

    private fun initListener() {
        binding.buttonRegister.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.editextEmail.text.toString().trim()
        val senha = binding.editextSenha.text.toString().trim()
        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                binding.progressBar.isVisible = true  // antes da chamada async: correto
                registerUser(email, senha)
                // o sucesso real só existe dentro do listener
            } else {
                showBottomSheet(message = getString(R.string.password_empty))
            }
        } else {
            showBottomSheet(message = getString(R.string.email_empty))
        }
    }
    
    private fun registerUser(email: String, password: String){
        try {
            val auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.progressBar.isVisible = false
                        findNavController().navigate(R.id.action_global_homeFragment)
                    } else {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), "Erro!", Toast.LENGTH_SHORT).show()
                    }
                }

        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }
    
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}