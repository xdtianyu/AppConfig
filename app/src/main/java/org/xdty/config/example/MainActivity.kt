package org.xdty.config.example

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.xdty.config.Config
import org.xdty.config.example.module.Status

class MainActivity : AppCompatActivity() {

    private val tag = javaClass.simpleName

    private lateinit var config: Config

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        config = Config.Builder()
            .endpoint("https://s3-tx.xdty.org")
            .accessKey("vAuKLADukB690VXlOr")
            .secretKey("bSi9tMs8pdWNgGpgYht5lxDWf76SAg5sdR5U")
            .build()

        asyncMethod1()
        asyncMethod2()
    }

    private fun asyncMethod1() {


        doAsync {
            val status = config.get(Status::class.java, "CallerInfo.json")
            uiThread {
                show(status)
            }
        }
    }

    private fun asyncMethod2() {
        GlobalScope.launch {
            val status = fetchConfig()

            runOnUiThread {
                show(status)
            }
        }
    }

    private suspend fun fetchConfig() = coroutineScope {
        config.get(Status::class.java, "CallerInfo.json")
    }

    private fun show(status: Status) {
        Log.d(tag, "status: $status")
        findViewById<TextView>(R.id.text).text = status.toString()
    }
}
