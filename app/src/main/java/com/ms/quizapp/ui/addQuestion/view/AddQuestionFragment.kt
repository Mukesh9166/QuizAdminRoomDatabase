package com.ms.quizapp.ui.addQuestion.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ms.quizapp.R
import com.ms.quizapp.data.models.Question
import com.ms.quizapp.ui.addQuestion.viewmodels.AddQuestionViewModel
import com.ms.quizapp.ui.addQuestion.viewmodels.AddQuestionViewModelFactory
import com.ms.quizapp.utils.composeComponents.BodyBoldText
import com.ms.quizapp.utils.composeComponents.MiniMediumText
import com.ms.quizapp.utils.showToast
import com.ms.quizapp.utils.theme.OrderAppTheme
import com.ms.quizapp.utils.theme. dark
import com.ms.quizapp.utils.theme.colorGrey
import com.ms.quizapp.utils.theme.colorWhite
import com.skydoves.landscapist.glide.GlideImage


class AddQuestionFragment : Fragment() {

    private var addQuestionViewModel: AddQuestionViewModel? = null
    private var showSuccessDialog = mutableStateOf(false)

    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        addQuestionViewModel = ViewModelProvider(
            this,
            AddQuestionViewModelFactory(activity?.application!!)
        )[AddQuestionViewModel::class.java]

        addQuestionViewModel?.addQuestionLoadStatus?.observe(this, Observer {
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
                        addQuestionViewModel, showSuccessDialog, context, false,
                        navController!!
                    )
                }
            }
        }
    }


}

@Composable
fun ShowSuccessDialog(
    showSuccessDialog: MutableState<Boolean>,
    navController: NavController,
    isUpdate: Boolean
) {

    val showStatus by showSuccessDialog


    if (showStatus) {
        AlertDialog(
            onDismissRequest = {

            },
            title = { Text(stringResource(R.string.success)) },
            text = {
                Text(
                    if (!isUpdate) stringResource(R.string.question_added_successfully) else stringResource(
                        R.string.question_updated_successfully
                    )
                )
            },
            confirmButton = {
                Button(onClick = {
                    showSuccessDialog.value = false
                    if (!isUpdate) {
                        val questionListFrag =
                            AddQuestionFragmentDirections.actionAddQuestionFragmentToViewQuestionsFragment()
                        navController.navigate(questionListFrag)
                    } else {
                        navController.popBackStack()
                    }

                }) {
                    Text(stringResource(R.string.ok))

                }
            }
        )
    }
}


@Composable
fun AddQuestionScreen(
    addQuestionViewModel: AddQuestionViewModel?,
    showSuccessDialog: MutableState<Boolean>,
    context: Context,
    isUpdate: Boolean,
    navController: NavController,
    question: Question? = null
) {

    var questionName = ""
    var questionImgUri: Uri? = null
    var option1=""
    var option2=""
    var option3=""
    var optionCorrect=0

    if (question != null) {
        questionName = question.question
         questionImgUri = if (!question.questionImageUri.isNullOrEmpty()) {
            Uri.parse(question.questionImageUri)
        } else {
            null
        }
         option1 = question.option1
         option2 = question.option2
         option3 = question.option3
         optionCorrect = question.correctAnswer

    }

    var questionText by remember { mutableStateOf(questionName) }
    var questionImageUri by remember { mutableStateOf<Uri?>(questionImgUri) }
    var option1Text by remember { mutableStateOf(option1) }
    var option2Text by remember { mutableStateOf(option2) }
    var option3Text by remember { mutableStateOf(option3) }
    var correctAnswer by remember { mutableStateOf(optionCorrect) }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> questionImageUri = uri }
    )

    val  colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colors.secondary,
        unfocusedBorderColor = MaterialTheme.colors.secondary,
        focusedLabelColor = MaterialTheme.colors.secondary,
        cursorColor = MaterialTheme.colors.primaryVariant,
        textColor = dark()

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OutlinedTextField(
            value = questionText,
            onValueChange = { questionText = it },
            label = {
                MiniMediumText(
                    color = colorGrey,
                    text = stringResource(R.string.enter_question)
                )
            },

            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors =colors

            )

        Button(
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        ) {
            BodyBoldText(
                color = colorWhite,
                text = stringResource(R.string.pick_image),
                modifier = Modifier.padding(5.dp),
            )
        }


        if (questionImageUri != null) {
            GlideImage(
                imageModel = questionImageUri ?: "",
                contentDescription = "Question Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, MaterialTheme.colors.onSurface),
                contentScale = ContentScale.Crop,
            )
        }

        OutlinedTextField(
            value = option1Text,
            onValueChange = { option1Text = it },
            label = {
                MiniMediumText(
                    color = colorGrey,
                    text = stringResource(R.string.option_one)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors =colors
        )

        OutlinedTextField(
            value = option2Text,
            onValueChange = { option2Text = it },
            label = {
                MiniMediumText(
                    color = colorGrey,
                    text = stringResource(R.string.option_two)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors =colors
        )

        OutlinedTextField(
            value = option3Text,
            onValueChange = { option3Text = it },
            label = {
                MiniMediumText(
                    color = colorGrey,
                    text = stringResource(R.string.option_three)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors =colors
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BodyBoldText(color =  dark(), text = "Select correct answer:")


            Row() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = correctAnswer == 1,
                        onClick = { correctAnswer = 1 },
                        modifier = Modifier.wrapContentWidth(),
                    )
                    BodyBoldText(color =  dark(), text = stringResource(R.string.option_one))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = correctAnswer == 2,
                        onClick = { correctAnswer = 2 },
                        modifier = Modifier.wrapContentWidth(),
                    )
                    BodyBoldText(color =  dark(), text = stringResource(R.string.option_two))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = correctAnswer == 3,
                        onClick = { correctAnswer = 3 },
                        modifier = Modifier.wrapContentWidth(),
                    )
                    BodyBoldText(color =  dark(), text = stringResource(R.string.option_three))
                }
            }

        }
        ShowSuccessDialog(showSuccessDialog, navController, isUpdate)
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            onClick = {
                if (questionText.isNotEmpty() && option1Text.isNotEmpty() &&
                    option2Text.isNotEmpty() && option3Text.isNotEmpty() && correctAnswer != 0
                ) {
                    if (!isUpdate) {
                        val newQuestion = Question(
                            question = questionText,
                            questionImageUri = questionImageUri?.toString(),
                            option1 = option1Text, option2 = option2Text, option3 = option3Text,
                            correctAnswer = correctAnswer
                        )

                        addQuestionViewModel?.addQuestion(newQuestion)
                    } else {
                        if (question != null) {
                            question.question = questionText
                            question.questionImageUri = questionImageUri?.toString()
                            question.option1 = option1Text
                            question.option2 = option2Text
                            question.option3 = option3Text
                            question.correctAnswer = correctAnswer

                            addQuestionViewModel?.updateQuestion(question)
                        }
                    }

                } else {
                    context.showToast(context.resources.getString(R.string.please_fill_in_all_fields))
                }
            }
        ) {
            BodyBoldText(
                color = colorWhite,
                text = stringResource(R.string.save),
                modifier = Modifier.padding(5.dp)
            )
        }
    }


}