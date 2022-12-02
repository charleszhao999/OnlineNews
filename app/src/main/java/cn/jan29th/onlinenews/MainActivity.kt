package cn.jan29th.onlinenews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    var newsList = ArrayList<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycleview: RecyclerView = findViewById(R.id.recycleview)
        val layoutManager = LinearLayoutManager(this)
        recycleview.layoutManager = layoutManager
        sendRequestWithOkHttp()
        val adapter = NewsAdapter(newsList)
        recycleview.adapter = adapter
    }

    private fun sendRequestWithOkHttp() {
        Thread(Runnable {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://106.12.97.199:8080/news_list_data.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseJSONWithJSONObject(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()

    }

    private fun parseJSONWithJSONObject(jsonData: String) {
        val gson = Gson()
        val typeOf = object : TypeToken<List<News>>() {}.type
        val newslist = gson.fromJson<List<News>>(jsonData, typeOf)
        for (news in newslist) {
            newsList.add(news)
        }
    }
}