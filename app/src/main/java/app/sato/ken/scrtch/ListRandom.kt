package app.sato.ken.scrtch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.ListActivity.Companion.randomList
import kotlinx.android.synthetic.main.activity_list_select.*

class ListRandom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_select)

        val listContext: String? = intent.getStringExtra(randomList)
        list_random.text = listContext
    }
}