package com.example.simplemath3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import io.github.kexanie.library.MathView
import kotlinx.android.synthetic.main.parent_quadratic_layout.view.*
import java.util.*


class ExpandableQuadraticAdapter(private var ctx: Context, var solveSteps: ArrayList<ArrayList<String>>, var explanationSteps: ArrayList<ArrayList<String>>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return 2
    }

    override fun getChildrenCount(parent: Int): Int {
        return solveSteps[parent].size
    }

    override fun getGroup(parent: Int): Any {
        return false
    }

    override fun getChild(parent: Int, child: Int): Any {
        return solveSteps[parent][child]
    }

    private fun getSecondaryChild(parent: Int, child: Int): Any {
        return explanationSteps[parent][child]
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
            convertView = inflater.inflate(R.layout.parent_quadratic_layout, parentview, false)
        }
        val parent_textview =
                convertView?.findViewById<View>(R.id.step) as TextView
        parent_textview.text = "Root ${parent+1}: step-by-step solution"
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
        child_textview.text = "\$\$ \\scriptsize{ ${getSecondaryChild(parent, child)} } \$\$"

        // set the solution to the current solve step
        val child_latexview =
                convertView.findViewById<View>(R.id.child_latex) as MathView
        child_latexview.text = "\\( ${getChild(parent, child)} \\)"

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }
}