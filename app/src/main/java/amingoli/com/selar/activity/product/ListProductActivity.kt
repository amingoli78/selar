package amingoli.com.selar.activity.product

import amingoli.com.selar.R
import amingoli.com.selar.adapter.ProductListManagerAdapter
import amingoli.com.selar.adapter.TagListAdapter
import amingoli.com.selar.helper.App
import amingoli.com.selar.model.Product
import amingoli.com.selar.model.TagList
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_list_product.*
import kotlinx.android.synthetic.main.activity_list_product.recyclerView
import kotlinx.android.synthetic.main.activity_list_product.recyclerView_tag
import kotlinx.android.synthetic.main.activity_list_product.toolbar
import kotlinx.android.synthetic.main.item_product.view.*

class ListProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_product)

        toolbar.title.text = "محصولات"

        initAdapterTagList()
        initAdapterListProducts()
    }

    private fun initAdapterTagList(){

        val array_tag = ArrayList<TagList>()
        array_tag.add(TagList("همه", R.drawable.ic_baseline_category_24,"all"))
        array_tag.add(TagList("پرفروش", R.drawable.ic_baseline_extension_24,"all"))
        array_tag.add(TagList("منتشرشده", R.drawable.ic_baseline_category_24,"all"))

        val adapterTagList = TagListAdapter(this,
            array_tag,
            object : TagListAdapter.Listener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onItemClicked(position: Int, item: TagList) {
                }
            })

        recyclerView_tag.adapter = adapterTagList
    }

    private fun initAdapterListProducts(){
        val listProducts = ArrayList<Product>(App.database.getAppDao().selectProduct())
        recyclerView.adapter = ProductListManagerAdapter(this,listProducts,object : ProductListManagerAdapter.Listener{
            override fun onItemClicked(position: Int, product: Product) {

            }

        })
    }
}