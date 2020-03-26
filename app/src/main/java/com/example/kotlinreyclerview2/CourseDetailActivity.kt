package com.example.kotlinreyclerview2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = CourseDetailAdapter()

        // we'll change the nav bar title..
        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle

        //println(courseDetailUrl)
        fetchJSON()
    }

    private fun fetchJSON() {
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY,-1)
        val courseDetailUrl = "http://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoId
        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailUrl).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                val gson = GsonBuilder().create()
                val courseLessons = gson.fromJson(body, Array<CourseLesson>::class.java)


                runOnUiThread {
                    recyclerView_main.adapter = CourseDetailAdapter()
                }

//                println(body)
            }

            override fun onFailure(call: Call?, e: IOException?) {
            }
        })
    }

    private class CourseDetailAdapter(): RecyclerView.Adapter<CourseLessonViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
//            val blueView = View(parent.context)
//            blueView.setBackgroundColor(Color.BLUE)
//            blueView.minimumHeight = 50
//            return CourseLessonViewHolder(blueView)

            val layoutInflater = LayoutInflater.from(parent.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)
            return CourseLessonViewHolder(customView)
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
        }

    }

    class CourseLessonViewHolder(val customView: View): RecyclerView.ViewHolder(customView) {

        companion object {
            val COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK"
        }

        init {
            customView.setOnClickListener {
                println("Attempt to load webview somehow???")
                val intent = Intent(customView.context, CourseLessonActivity::class.java)
                customView.context.startActivity(intent)
            }
        }
    }
}
