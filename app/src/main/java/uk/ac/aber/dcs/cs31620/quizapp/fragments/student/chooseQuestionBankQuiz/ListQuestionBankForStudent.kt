package uk.ac.aber.dcs.cs31620.quizapp.fragments.student.chooseQuestionBankQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.ac.aber.dcs.cs31620.quizapp.R


class ListQuestionBankForStudent : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //RecycleView For listing QuestionBank

        return inflater.inflate(R.layout.fragment_list_question_bank, container, false)
    }

}