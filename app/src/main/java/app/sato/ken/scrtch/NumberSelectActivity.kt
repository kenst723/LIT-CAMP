package app.sato.ken.scrtch

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.NumberActivity.Companion.keyF
import app.sato.ken.scrtch.NumberActivity.Companion.keyS
import kotlinx.android.synthetic.main.activity_scratch.*

class NumberSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scratch)
        //例外の可能性
        val result = kotlin.runCatching {
            val message: String? = intent.getStringExtra(keyF)

            val message2: String? = intent.getStringExtra(keyS)

            val intF = Integer.parseInt(message.toString())
            val intS = Integer.parseInt(message2.toString())

            val random = (intF..intS).random()
            finalShow.text = random.toString()
            //失敗時

        }.onFailure {

            Toast.makeText(
                applicationContext,
                "入力エラーです！",
                Toast.LENGTH_SHORT

            ).show()
            //成功時
        }.onSuccess {

            Toast.makeText(applicationContext, "おめでとう！", Toast.LENGTH_LONG).show()

        }


        val shirokuma: Typeface = Typeface.createFromAsset(assets, "shirokuma-Regular.otf")

        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        finalShow.typeface = kodomoFont
        stop.typeface = shirokuma


        stop.setOnClickListener {
            finish()
        }
    }
}