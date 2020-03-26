package com.example.kotlinreyclerview2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_course_lesson.*

class CourseLessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_course_lesson)
        webview_course_lesson.setBackgroundColor(Color.YELLOW)
        webview_course_lesson.loadUrl("https://www.google.com")

//        val courseLink = intent.getStringExtra(CourseDetailActivity.CourseLessonViewHolder.COURSE_LESSON_LINK_KEY)

//        webview_course_lesson.settings.javaScriptEnabled = true
//        webview_course_lesson.settings.loadWithOverviewMode = true
//        webview_course_lesson.settings.useWideViewPort = true

//        webview_course_lesson.loadUrl(courseLink)
    }

}
