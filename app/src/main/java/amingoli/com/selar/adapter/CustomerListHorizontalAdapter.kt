package amingoli.com.selar.adapter

import amingoli.com.selar.R
import amingoli.com.selar.model.TagList
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_customer_horizontal.view.*

class CustomerListHorizontalAdapter(val context: Context,
                                    val list: ArrayList<TagList>,
                                    val listener: Listener
) : RecyclerView.Adapter<CustomerListHorizontalAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener{
        fun onItemClicked(position: Int, item: TagList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_customer_horizontal, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = holder.itemView
        val model = list[position]

        item.title.setText("${model.title}")
        item.content.text = model.tag

        item.setOnClickListener {
            listener?.onItemClicked(position,model)
        }
    }

    fun addItem(item: TagList){
        list.add(list.size,item)
        notifyItemInserted(list.size)
    }

    fun addItem(item: TagList, position: Int){
        if (position == -1) addItem(item)
        else {
            Log.e("qqq", "addItem status is pos: $position" )
            list[position] = item
            notifyItemChanged(position,item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list : List<TagList>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}