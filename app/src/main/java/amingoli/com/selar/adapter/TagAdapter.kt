package amingoli.com.selar.adapter

import amingoli.com.selar.R
import amingoli.com.selar.model.TagList
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_tag_info.view.*

class TagAdapter(val context: Context,
                 val list: ArrayList<TagList>,
                 val listener: Listener?
) : RecyclerView.Adapter<TagAdapter.ListViewHolder>() {

    private var position_selected = -1

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener{
        fun onItemClicked(position: Int, item: TagList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tag, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ListViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item = holder.itemView
        val model = list[position]

        item.text_title.text = model.title

        if (listener != null){
            setItemSelected(position_selected != -1 && position_selected == position,item.text_title)

            item.setOnClickListener {
                val back_position_selected = position_selected
                position_selected = position
                listener.onItemClicked(position,model)
                notifyItemChanged(position_selected, model)
                notifyItemChanged(back_position_selected, model)
            }
        }
    }

    private fun setItemSelected(is_selected:Boolean, textView : TextView){
        if (is_selected){
            textView.setTextColor(ContextCompat.getColor(context,R.color.white))
            textView.backgroundTintList = ContextCompat.getColorStateList(context, R.color.blue)

        }else{
            textView.setTextColor(ContextCompat.getColor(context,R.color.black))
            textView.backgroundTintList = ContextCompat.getColorStateList(context, R.color.gray)
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

    fun updateItemSelected(item_selected: Int){
        val back_position_selected = position_selected
        position_selected = item_selected
        notifyItemChanged(position_selected)
        notifyItemChanged(back_position_selected)
    }
}