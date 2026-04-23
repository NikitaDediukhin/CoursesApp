package com.example.feature_login.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_login.databinding.FragmentLoginBinding
import androidx.core.net.toUri

class LoginFragment : Fragment() {

    interface Listener {
        fun onLoginSuccess()
    }

    private var listener: Listener? = null

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = requireNotNull(_binding) {
            "Binding is not initialized or already cleared"
        }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateLoginButtonState()
        }
        override fun afterTextChanged(s: Editable?) = Unit
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInputs()
        setupClicks()
        updateLoginButtonState()
    }

    private fun setupInputs() {
        binding.emailEditText.addTextChangedListener(textWatcher)
        binding.passwordEditText.addTextChangedListener(textWatcher)
    }

    private fun setupClicks() {
        binding.loginButton.setOnClickListener {
            listener?.onLoginSuccess()
        }

        binding.vkButton.setOnClickListener {
            openUrl("https://vk.com/")
        }

        binding.okButton.setOnClickListener {
            openUrl("https://ok.ru/")
        }
    }

    private fun updateLoginButtonState() {
        val email = binding.emailEditText.text?.toString().orEmpty().trim()
        val password = binding.passwordEditText.text?.toString().orEmpty()

        binding.loginButton.isEnabled =
            isValidEmail(email) && password.isNotBlank()
    }

    private fun isValidEmail(email: String): Boolean {
        if (email.isBlank()) return false
        if (email.any { it in 'А'..'я' || it == 'ё' || it == 'Ё' }) return false
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
    }

    override fun onDestroyView() {
        binding.emailEditText.removeTextChangedListener(textWatcher)
        binding.passwordEditText.removeTextChangedListener(textWatcher)
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}