package com.example.simplemath3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import io.github.kexanie.library.MathView
import java.util.*


class ExpandableAdapter(private var ctx: Context, var solveSteps: ArrayList<String>, var explanationSteps: ArrayList<String>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return 1
    }

    override fun getChildrenCount(parent: Int): Int {
        return solveSteps.size
    }

    override fun getGroup(parent: Int): Any {
        return false
    }

    override fun getChild(parent: Int, child: Int): Any {
        return solveSteps[child]
    }

    private fun getSecondaryChild(parent: Int, child: Int): Any {
        return explanationSteps[child]
    }

    override fun getGroupId(parent: Int): Long {
        return parent.toLong()
    }

    override fun getChildId(parent: Int, child: Int): Long {
        return child.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
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

        // set the hint to the current explanation step
        val child_textview =
            convertView?.findViewById<View>(R.id.child_txt) as MathView
        child_textview.text = "\$\$\\small{${getSecondaryChild(parent, child)}}\$\$"

        // set the solution to the current solve step
        val child_latexview =
            convertView.findViewById<View>(R.id.child_latex) as MathView
        child_latexview.text = getChild(parent, child).toString()

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }
}