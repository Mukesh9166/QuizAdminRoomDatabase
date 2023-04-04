package com.ms.quizapp.ui.viewQuestions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ms.quizapp.R
import com.ms.quizapp.data.models.Question
import com.ms.quizapp.ui.viewQuestions.viewmodels.ViewQuestionViewModel
import com.ms.quizapp.ui.viewQuestions.viewmodels.ViewQuestionViewModelFactory
import com.ms.quizapp.utils.composeComponents.BodyBoldText
import com.ms.quizapp.utils.composeComponents.BodyText
import com.ms.quizapp.utils.theme.OrderAppTheme
import com.ms.quizapp.utils.theme.dark
import com.skydoves.landscapist.glide.GlideImage


class ViewQuestionsFragment : Fragment() {
    private var TAG = "ViewQuestionsFragment"

    private var viewQuestionViewModel: ViewQuestionViewModel? = null
    private var navController: NavController? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHostHome) as NavHostFragment
        navController = navHostFragment.navController


        viewQuestionViewModel = ViewModelProvider(
            this,
            ViewQuestionViewModelFactory(activity?.application!!)
        )[ViewQuestionViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return ComposeView(requireContext()).apply {
            setContent {
                OrderAppTheme {
                    ViewQuestionScreen()
                }
            }
        }
    }

    @Composable
    fun ViewQuestionScreen() {
        val lazyPagingItems: LazyPagingItems<Question>? =
            viewQuestionViewModel?.allQuestionsPaged?.collectAsLazyPagingItems()





        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            if (lazyPagingItems?.itemCount == 0) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),

                    ) {

                    BodyBoldText(
                        text = stringResource(R.string.no_questions_found),
                        color = dark()
                    )
                }
            } else {

                LazyColumn {

                    if (lazyPagingItems != null)
                        items(lazyPagingItems) {
                            QuestionListItem(
                                question = it!!,
                                onEditClick = {
                                    var action =
                                        ViewQuestionsFragmentDirections.actionViewQuestionsFragmentToUpdateQuestionFragment(
                                            it
                                        )
                                    navController?.navigate(action)
                                },
                                onDeleteClick = {
                                    it.let { it1 ->
                                        viewQuestionViewModel?.deleteQuestion(
                                            it1
                                        )
                                    }

                                    //arrQues?.removeAt(position)

                                }
                            )
                        }
                }


            }
        }
    }

    @Composable
    fun QuestionListItem(
        question: Question,
        onEditClick: () -> Unit,
        onDeleteClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .clickable(onClick = onEditClick)
                    .padding(16.dp)
            ) {
                BodyBoldText(
                    overflow = TextOverflow.Ellipsis,
                    color = dark(),
                    text = question.question,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (!question.questionImageUri.isNullOrEmpty()) {
                    GlideImage(
                        imageModel = question.questionImageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 8.dp)
                    )
                }
                BodyText(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = dark(),
                    text = "Option 1: ${question.option1}",
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                BodyText(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = dark(),
                    text = "Option 2: ${question.option2}",
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                BodyText(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = dark(),
                    text = "Option 3: ${question.option3}",
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                BodyText(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = dark(),
                    text = "Correct Answer: Option ${question.correctAnswer}",
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = onEditClick) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(Icons.Filled.Delete, contentDescription = null)
                    }
                }
            }
        }
    }
}





