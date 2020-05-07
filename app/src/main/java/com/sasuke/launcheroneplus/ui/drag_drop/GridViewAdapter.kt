package com.sasuke.launcheroneplus.ui.drag_drop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.huxq17.handygridview.scrollrunner.OnItemMovedListener
import com.sasuke.launcheroneplus.R
import com.sasuke.launcheroneplus.data.AppInfo
import com.sasuke.launcheroneplus.ui.launcher.apps.AppViewHolder

class GridViewAdapter : BaseAdapter(), OnItemMovedListener, AppViewHolder.OnClickListeners {

    private lateinit var onClickListeners: OnClickListeners
    val list: MutableList<AppInfo> = ArrayList()
    private var inEditMode = false


    override fun getView(position: Int, convertview: View?, parent: ViewGroup): View {
        val appViewHolder: AppViewHolder
        val myView: View =
            convertview
                ?: LayoutInflater.from(parent.context).inflate(
                    R.layout.cell_app_info,
                    parent,
                    false
                )
        appViewHolder = AppViewHolder(myView, false)
        appViewHolder.setAppInfo(getItem(position) as AppInfo)
        appViewHolder.setOnClickListeners(this)
        return myView
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun onItemMoved(from: Int, to: Int) {
        val appInfo = list.removeAt(from)
        list.add(to, appInfo)
    }

    override fun isFixed(position: Int): Boolean {
        return false
    }

    fun addItem(appInfo: AppInfo): Boolean {
        if (!list.contains(appInfo)) {
            list.add(appInfo)
            notifyDataSetChanged()
            return true
        }
        return false
    }

    fun setInEditMode(inEditMode: Boolean) {
        this.inEditMode = inEditMode
        notifyDataSetChanged()
    }

    interface OnClickListeners {
        fun onItemClick(position: Int, parent: View, appInfo: AppInfo)
    }

    fun setOnClickListeners(onClickListeners: OnClickListeners) {
        this.onClickListeners = onClickListeners
    }

    override fun onItemClick(position: Int, parent: View, appInfo: AppInfo) {
        if (::onClickListeners.isInitialized)
            onClickListeners.onItemClick(position, parent, appInfo)
    }

    override fun onItemLongClick(position: Int, parent: View, appInfo: AppInfo) {

    }
}