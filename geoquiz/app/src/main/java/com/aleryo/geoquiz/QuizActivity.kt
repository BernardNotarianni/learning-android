package com.aleryo.geoquiz

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import android.widget.TextView
import com.aleryo.geoquiz.R.id.question_text_view


class QuizActivity : AppCompatActivity() {

    private val TAG = "QuizActivity"
    private val KEY_INDEX = "index"

    private var mTrueButton: Button? = null
    private var mFalseButton: Button? = null
    private var mPrevButton: ImageButton? = null
    private var mNextButton: ImageButton? = null
    private var mQuestionTextView: TextView? = null

    private val mQuestionBank = arrayOf(Question(R.string.question_oceans, true), Question(R.string.question_mideast, false), Question(R.string.question_africa, false), Question(R.string.question_americas, true), Question(R.string.question_asia, true))
    private var mCurrentIndex = 0

    private fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].mTextResId
        mQuestionTextView!!.setText(question)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].mAnswerTrue
        var messageResId: Int
        messageResId = if (userPressedTrue == answerIsTrue) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz)

        mQuestionTextView = findViewById<View>(question_text_view) as TextView
        val question = mQuestionBank[mCurrentIndex].mTextResId
        mQuestionTextView!!.setText(question)

        mTrueButton = findViewById(R.id.true_button)
        mTrueButton?.setOnClickListener({
            checkAnswer(true)
        })

        mFalseButton = findViewById(R.id.false_button)
        mFalseButton?.setOnClickListener({
            checkAnswer(false)
        })

        mPrevButton = findViewById<View>(R.id.prev_button) as ImageButton
        mPrevButton!!.setOnClickListener({
            mCurrentIndex = (mCurrentIndex-1).rem(mQuestionBank.size)
            if (mCurrentIndex < 0) mCurrentIndex += mQuestionBank.size
            val question = mQuestionBank[mCurrentIndex].mTextResId
            mQuestionTextView!!.setText(question)
        })

        mNextButton = findViewById<View>(R.id.next_button) as ImageButton
        mNextButton!!.setOnClickListener({
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            val question = mQuestionBank[mCurrentIndex].mTextResId
            mQuestionTextView!!.setText(question)
        })

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
        }
        updateQuestion()
        /*
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        super.onSaveInstanceState(savedInstanceState)
        Log.d(TAG, "onSaveInstanceState")
        savedInstanceState?.putInt(KEY_INDEX, mCurrentIndex)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_quiz, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


}
