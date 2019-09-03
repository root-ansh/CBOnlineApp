package com.codingblocks.cbonlineapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingblocks.cbonlineapp.R
import com.codingblocks.cbonlineapp.database.models.SectionWithContent
import com.codingblocks.cbonlineapp.util.Animations.expand
import kotlinx.android.synthetic.main.item_section.view.*

class SectionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_section, parent, false)) {

    var sectionContent: SectionWithContent? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(sectionContent: SectionWithContent?) {
        this.sectionContent = sectionContent
        itemView.title.text = sectionContent?.section?.name
        val ll = itemView.findViewById<LinearLayout>(R.id.sectionContents)
        if (ll.visibility == View.VISIBLE) {
            ll.removeAllViews()
            expand(ll)
        } else {
            ll.removeAllViews()
            ll.visibility = View.GONE
        }
        itemView.lectures.text = "0/${sectionContent?.content?.size} Lectures Completed"
        var duration: Long = 0
        var sectionComplete = 0
        sectionContent?.content?.forEach { content ->
            val factory = LayoutInflater.from(itemView.context)
            val inflatedView = factory.inflate(R.layout.item_section_detailed_info, ll, false)
            val subTitle = inflatedView.findViewById(R.id.textView15) as TextView
            val downloadBtn = inflatedView.findViewById(R.id.downloadBtn) as ImageView
            val contentType = inflatedView.findViewById(R.id.contentType) as ImageView
            subTitle.text = content.title
            ll.addView(inflatedView)

            itemView.setOnClickListener {
                expand(ll)
            }

            itemView.arrow.setOnClickListener {
                expand(ll)
            }
        }

    }
}
