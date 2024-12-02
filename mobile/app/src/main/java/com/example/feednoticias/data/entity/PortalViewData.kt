package com.example.feednoticias.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "portal_table")
data class PortalViewData(
    @PrimaryKey val id: String,
    val titulo: String?,
    val descricao: String?,
    val imagem: String?,
    val url: String?,
    val date: String?,
    val chapeu: String?,
    val type: String
) : Serializable
