package com.arthur_loureiro.task.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.arthur_loureiro.task.R
import com.arthur_loureiro.task.databinding.FragmentLoginBinding
import com.arthur_loureiro.task.util.showBottomSheet
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // variável da API de autenticação
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        initListener()
    }

    private fun initListener(){
        binding.buttonLogin.setOnClickListener {
            validateData()
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }
    }

    private fun validateData() {
        val email = binding.editextEmail.text.toString().trim()
        val senha = binding.editextSenha.text.toString().trim()
        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                loginUser(email, senha)
            } else {
                showBottomSheet(message = getString(R.string.password_empty))
            }
        } else {
            showBottomSheet(message = getString(R.string.email_empty))
        }

    }


    private fun loginUser(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_global_homeFragment)
                    } else {
                        Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}