package com.arthur_loureiro.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthur_loureiro.task.R
import com.arthur_loureiro.task.data.model.Status
import com.arthur_loureiro.task.data.model.Task
import com.arthur_loureiro.task.databinding.FragmentHomeBinding
import com.arthur_loureiro.task.databinding.FragmentTodoBinding
import com.arthur_loureiro.task.ui.adapter.TaskAdapter

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerViewTask(getTask())
    }

    private fun initListeners() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate((R.id.action_homeFragment_to_formTaskFragment))
    }}

    private fun initRecyclerViewTask(taskList: List<Task>){

        taskAdapter = TaskAdapter(requireContext(), taskList)
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.setHasFixedSize(true)

        binding.recyclerViewTask.adapter = taskAdapter

    }

    private fun getTask() = listOf(

        Task(id = "0", description = "Criar nova tela do app", Status.TODO),
        Task(id = "0", description = "Validar informações na tela de login", Status.TODO),
        Task(id = "0", description = "Adicionar nova funcionalidade no app", Status.TODO),
        Task(id = "0", description = "Salvar token localmente", Status.TODO),
        Task(id = "0", description = "Criar funcionalide de logout no app", Status.TODO),

        )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}