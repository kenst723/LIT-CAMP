package app.sato.ken.scrtch

import android.content.Intent
import android.graphics.Typeface
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_number.*

class NumberActivity : AppCompatActivity() {
    companion object {
        const val keyF = "keyF"
        const val keyS = "keyS"
    }

    private lateinit var soundPool: SoundPool
    private var soundOne = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(2)
            .build()

        soundOne = soundPool.load(this, R.raw.win, 1)

        stop.setOnClickListener {
            finish()
        }

        add.setOnClickListener {

            val subF = first.text.toString()
            val subS = second.text.toString()

            val intent = Intent(applicationContext, NumberSelectActivity::class.java)

            intent.putExtra(keyF, subF)
            intent.putExtra(keyS, subS)

            soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1.0f)

            startActivity(intent)
        }

        val shirokuma: Typeface = Typeface.createFromAsset(assets, "shirokuma-Regular.otf")
        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        first.typeface = kodomoFont
        second.typeface = kodomoFont
        add.typeface = kodomoFont
    }
}