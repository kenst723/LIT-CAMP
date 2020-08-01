package app.sato.ken.scrtch

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Typeface.createFromAsset(assets, "shirokuma-Regular.otf")
        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        number.typeface = kodomoFont
        string.typeface = kodomoFont

        number.setOnClickListener {
            val intent = Intent(
                applicationContext,
                NumberActivity::class.java
            )
            startActivity(intent)
        }

        string.setOnClickListener {
            val intent = Intent(
                applicationContext,
                ListActivity::class.java
            )
            startActivity(intent)
        }
    }
}
