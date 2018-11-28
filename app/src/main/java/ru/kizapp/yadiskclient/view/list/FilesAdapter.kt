package ru.kizapp.yadiskclient.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yandex.disk.rest.json.Resource
import kotlinx.android.synthetic.main.item_file.view.*
import kotlinx.android.synthetic.main.item_folder.view.*
import ru.kizapp.yadiskclient.R
import java.text.DecimalFormat
import java.util.ArrayList

class FilesAdapter(private val mListener: OnResourceClicked) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_FOLDER = 0
        private const val ITEM_FILE = 1
        private const val ITEM_DUMMY = 99
    }

    val mFiles = ArrayList<Resource>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        when (viewType) {
            ITEM_FOLDER -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false)
                val holder = FolderViewHolder(view)
                holder.itemView.setOnClickListener {
                    mListener.onItemClick(mFiles[holder.adapterPosition])
                }
                return holder
            }
            ITEM_FILE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false)
                val holder = FileViewHolder(view)
                holder.itemView.setOnClickListener {
                    mListener.onItemClick(mFiles[holder.adapterPosition])
                }
                return holder
            }
            ITEM_DUMMY -> {
                val view = Space(context)
                val height = context.resources.getDimensionPixelSize(R.dimen.dummy_view_size)
                val params = RecyclerView.LayoutParams(0, height)
                view.layoutParams = params
                return EmptyViewHolder(view)
            }
        }
        throw IllegalArgumentException("Unknown viewType $viewType")
    }

    override fun getItemViewType(position: Int): Int {
        if (position == mFiles.lastIndex + 1) {
            return ITEM_DUMMY
        }
        return if (mFiles[position].isDir) {
            ITEM_FOLDER
        } else {
            ITEM_FILE
        }
    }

    override fun getItemCount(): Int = mFiles.size + 1

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
                holder.fileSizeTextView.text = readableFileSize(mFiles[position].size)
            }
        }
    }

}

class FolderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dirNameTextView: TextView = view.tvDirName
}

class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.tvFileName
    val fileSizeTextView = view.tvFileSize
}

class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

interface OnResourceClicked {
    fun onItemClick(resource: Resource)
}

fun readableFileSize(size: Long?): String {
    if (size == null) return "0"
    if (size <= 0) return "0"
    val units = arrayOf("B", "kB", "MB", "GB", "TB")
    val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
    return DecimalFormat("#,##0.#").format(size / Math.pow(1024.0, digitGroups.toDouble())) + " " + units[digitGroups]
}