package com.example.simplemath3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import java.util.*


class ExpandableAdapter(var ctx: Context, var childList: ArrayList<ArrayList<String>>, var parents: Array<String>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return childList.size
    }

    override fun getChildrenCount(parent: Int): Int {
        return childList[parent].size
    }

    override fun getGroup(parent: Int): Any {
        return parents[parent]
    }

    override fun getChild(parent: Int, child: Int): Any {
        return childList[parent][child]
    }

    override fun getGroupId(parent: Int): Long {
        return parent.toLong()
    }

    override fun getChildId(parent: Int, child: Int): Long {
        return child.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        parent: Int,
        isExpanded: Boolean,
        convertView: View?,
        parentview: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.parent_layout, parentview, false)
        }
        return convertView!!
    }

    override fun getChildView(
        parent: Int,
        child: Int,
        isLastChild: Boolean,
        convertView: View?,
        parentview: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.child_layout, parentview, false)
        }
        val child_textvew =
            convertView?.findViewById<View>(R.id.child_txt) as TextView
        child_textvew.text = getChild(parent, child).toString()
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    init {
        this.childList = childList
        this.parents = parents
    }
}