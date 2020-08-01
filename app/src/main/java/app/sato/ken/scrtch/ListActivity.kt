package app.sato.ken.scrtch

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity() {

    var dataList = mutableListOf<RowModel>()

    private var resultList = mutableListOf<String>()

    companion object {
        const val randomList = "random"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)



        val recyclerView = recycler_view

        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")

        stop.typeface =  kodomoFont
        content.typeface = kodomoFont
        add.typeface = kodomoFont
        start.typeface = kodomoFont

        val adapter = ViewAdapter(dataList, object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                Toast.makeText(applicationContext, rowModel.detail, Toast.LENGTH_LONG).show()
            }
        })


        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)


        // インターフェースの実装

        add.setOnClickListener {

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                LinearLayoutManager(applicationContext)
            recyclerView.adapter = adapter

                val data: RowModel = RowModel().also {
                    it.detail = content.text.toString()
                }


                dataList.add(data)
                resultList.add(data.toString())
        }

        content.setOnFocusChangeListener { view, _ ->
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        start.setOnClickListener {
            val random = resultList.random()

            val intent = Intent(applicationContext,ListRandom::class.java)
            intent.putExtra(randomList,random)
            startActivity(intent)
        }

        stop.setOnClickListener{
            finish()
        }
    }


    //カードのスワイプアクションの定義
    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<HomeViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            //スワイプ時に実行
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //データリストからスワイプしたデータを削除
                dataList.removeAt(viewHolder.adapterPosition)

                //リストからスワイプしたカードを削除
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })

}
