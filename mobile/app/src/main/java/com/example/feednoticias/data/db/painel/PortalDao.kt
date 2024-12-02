package com.example.feednoticias.data.db.painel

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feednoticias.data.entity.PortalViewData

interface PortalDao {

    @Query("SELECT * FROM portal_table")
    suspend fun getAllPortalData(): List<PortalViewData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortalData(portalViewData: List<PortalViewData>)

    @Query("DELETE FROM portal_table")
    suspend fun deleteAllPortalData()
}