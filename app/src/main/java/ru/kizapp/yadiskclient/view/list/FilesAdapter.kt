package ru.kizapp.yadiskclient.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yandex.disk.rest.json.Resource
import kotlinx.android.synthetic.main.item_file.view.*
import kotlinx.android.synthetic.main.item_folder.view.*
import ru.kizapp.yadiskclient.R
import java.util.ArrayList

class FilesAdapter(private val mListener: OnResourceClicked) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_FOLDER = 0
        private const val ITEM_FILE = 1
    }

    val mFiles = ArrayList<Resource>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_FOLDER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false)
                val holder = FolderViewHolder(view)
                holder.itemView.setOnClickListener {
                    mListener.onItemClick(mFiles[holder.adapterPosition])
                }
                return holder
            }
            ITEM_FILE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
                val holder = FileViewHolder(view)
                holder.itemView.setOnClickListener {
                    mListener.onItemClick(mFiles[holder.adapterPosition])
                }
                return holder
            }
        }
        throw IllegalArgumentException("Unknown viewType $viewType")
    }

    override fun getItemViewType(position: Int): Int {
        return if (mFiles[position].isDir) {
            ITEM_FOLDER
        } else {
            ITEM_FILE
        }
    }

    override fun getItemCount(): Int = mFiles.size

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        when (type) {
            ITEM_FOLDER -> {
                val holder = vh as FolderViewHolder
                holder.dirNameTextView.text = mFiles[position].name
            }
            ITEM_FILE -> {
                val holder = vh as FileViewHolder
                holder.titleTextView.text = mFiles[position].name
//                holder.subtitleTextView.text = mFiles[position].mediaType
            }
        }
    }

}

class FolderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dirNameTextView: TextView = view.tvDirName
}

class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.tvFileName
//    val subtitleTextView: TextView = view.findViewById(android.R.id.text2)
}

interface OnResourceClicked {
    fun onItemClick(resource: Resource)
}