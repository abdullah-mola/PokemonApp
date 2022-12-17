package com.example.pojo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Pokemon(@PrimaryKey(autoGenerate = true) val id:Int, val name:String, var url:String ,val img:Bitmap)
