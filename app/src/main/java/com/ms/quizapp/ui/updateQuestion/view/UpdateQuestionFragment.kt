package com.ms.quizapp.ui.updateQuestion.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ms.quizapp.R
import com.ms.quizapp.data.models.Question
import com.ms.quizapp.ui.addQuestion.view.AddQuestionScreen
import com.ms.quizapp.ui.addQuestion.viewmodels.AddQuestionViewModel
import com.ms.quizapp.ui.addQuestion.viewmodels.AddQuestionViewModelFactory
import com.ms.quizapp.utils.theme.OrderAppTheme

class UpdateQuestionFragment : Fragment() {

    private var addQuestionViewModel: AddQuestionViewModel? = null
    private var showSuccessDialog = mutableStateOf(false)

    private var navController: NavController? = null

    private var question: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("question")) {
                question = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getSerializable("question", Question::class.java)
                } else {
                    it.getSerializable("question") as Question
                }
            }
        }

        addQuestionViewModel = ViewModelProvider(
            this,
            AddQuestionViewModelFactory(activity?.application!!)
        )[AddQuestionViewModel::class.java]

        addQuestionViewModel?.updateQuestionLoadStatus?.observe(this, Observer {
            if (it) {
                showSuccessDialog.value = true
            }
        })

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHostHome) as NavHostFragment
        navController = navHostFragment.navController
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return ComposeView(requireContext()).apply {
            setContent {
                OrderAppTheme {
                    AddQuestionScreen(
                        addQuestionViewModel, showSuccessDialog, context, true,
                        navController!!, question = question
                    )
                }
            }
        }
    }

}