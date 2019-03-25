package gray.dev.connections.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import gray.dev.connections.R
import gray.dev.connections.model.Connections
import kotlinx.android.synthetic.main.item_connection_layout.view.*

class ConnectionsAdapter(private var context: Context, private val mConnectionList: List<Connections>,
                         private val rateColor: Int, private val tab: String) : RecyclerView.Adapter<ConnectionsAdapter.ConnectionViewHolder>() {

    private var selectedItemCount = 0
    internal var selectedItemPosition = -1
    private var listener: SelectionCountListener
    private var isSelectingMode: Boolean = false
    var isItemRemoved: Boolean = false

    init {
        this.listener = context as SelectionCountListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionsAdapter.ConnectionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_connection_layout, parent, false)
        return ConnectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mConnectionList.size
    }

    override fun onBindViewHolder(holder: ConnectionsAdapter.ConnectionViewHolder, position: Int) {
        val connections = mConnectionList[position]
        holder.imgFollower.setImageResource(connections.img!!)
        holder.txtRating.text = connections.rating.toString()
        holder.txtTitle.text = connections.name
        holder.txtTaskCount.text = connections.taskCompleted.toString() + " Tasks"
        holder.rlRating.setBackgroundResource(rateColor)

        if (isSelectingMode) {
            holder.imgCheckDisabled.visibility = View.VISIBLE
        } else {
            holder.imgCheckDisabled.visibility = View.GONE
        }


        holder.imgCheckDisabled.setOnClickListener {
            connections.isSelected = true
            if (connections.isSelected) {
                holder.imgCheckDisabled.visibility = View.GONE
                holder.imgCheckEnabled.visibility = View.VISIBLE
                holder.itemParent.setBackgroundResource(R.drawable.selected_rect)
                selectedItemCount += 1
                listener.onConnectionItemSelected(selectedItemCount, tab)
            }
        }

        holder.imgCheckEnabled.setOnClickListener {
            connections.isSelected = false
            if (!connections.isSelected) {
                holder.imgCheckDisabled.visibility = View.VISIBLE
                holder.imgCheckEnabled.visibility = View.GONE
                holder.itemParent.setBackgroundResource(0)
                selectedItemCount -= 1
                listener.onConnectionItemSelected(selectedItemCount, tab)
            }
        }

        holder.cvConnections.setOnLongClickListener {
            isSelectingMode = true
            notifyDataSetChanged()
            return@setOnLongClickListener true
        }
    }


    inner class ConnectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgFollower: ImageView = itemView.imgFollower
        var txtRating: TextView = itemView.txtRating
        var txtTitle: TextView = itemView.txtTitle
        var txtTaskCount: TextView = itemView.txtTaskCount
        var rlRating: RelativeLayout = itemView.rlRating
        var imgCheckEnabled: ImageView = itemView.imgCheckEnabled
        var imgCheckDisabled: ImageView = itemView.imgCheckDisabled
        var itemParent: ConstraintLayout = itemView.itemParent
        var cvConnections: CardView = itemView.cvConnections

    }

    interface SelectionCountListener {
        fun onConnectionItemSelected(count: Int, tab: String)
    }
}