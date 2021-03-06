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

class TagInfoAdapter(val context: Context,
                     val list: ArrayList<TagList>,
                     val listener: Listener?
) : RecyclerView.Adapter<TagInfoAdapter.ListViewHolder>() {

    var position_selected = 0

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Listener{
        fun onItemClicked(position: Int, item: TagList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tag_info, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = holder.itemView
        val model = list[position]

        setItemSelected(position_selected != -1 && position_selected == position,
            item.image_icon,item.text_title)

        item.text_title.text = model.title
        if (model.icon != null) {
            item.image_icon.visibility = View.VISIBLE
            item.image_icon.setImageDrawable(ContextCompat.getDrawable(context,model.icon!!))
        }
        else item.image_icon.visibility = View.GONE

        item.setOnClickListener {
            val back_position_selected = position_selected
            position_selected = position
            listener?.onItemClicked(position,model)
            notifyItemChanged(position_selected, model)
            notifyItemChanged(back_position_selected, model)
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

    fun removeSelection(){
        val p = position_selected
        position_selected = -1
        notifyItemChanged(p)
    }

    private fun setItemSelected(is_selected:Boolean, imageView:ImageView, textView : TextView){
        if (is_selected){
            imageView.setBackgroundColor(ContextCompat.getColor(context,R.color.blue))
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN)
            textView.setTextColor(ContextCompat.getColor(context,R.color.blue))
        }else{
            imageView.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
            textView.setTextColor(ContextCompat.getColor(context,R.color.black))
        }
    }
}