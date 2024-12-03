package com.example.feednoticias.presentation.portal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feednoticias.data.entity.PortalViewData
import com.example.feednoticias.databinding.FragmentPortalBinding

class PortalAdapter(
    private val feed: List<PortalViewData>,
    private val onItemClick: (PortalViewData) -> Unit) :
    RecyclerView.Adapter<PortalAdapter.PortalViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalViewHolder {
        val view = FragmentPortalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PortalViewHolder(view)
    }

    override fun getItemCount(): Int = feed.size

    override fun onBindViewHolder(holder: PortalViewHolder, position: Int) {
        holder.bind(feed[position])

    }

    inner class PortalViewHolder(binding: FragmentPortalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(portalViewData: PortalViewData) {


            itemView.setOnClickListener { onItemClick(portalViewData) }
        }
    }
}