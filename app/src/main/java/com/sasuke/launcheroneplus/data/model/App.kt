package com.sasuke.launcheroneplus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class App(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val icon: String,
    val packageName: String,
    val label: String,
    var isSelected: Boolean = false,
    @ColumnInfo(name = "isHidden", index = true)
    var isHidden: Boolean = false
)