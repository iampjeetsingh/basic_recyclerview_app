package io.github.iampjeetsingh.assignment.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val imageUrl: String?,
    val about: String?
)
