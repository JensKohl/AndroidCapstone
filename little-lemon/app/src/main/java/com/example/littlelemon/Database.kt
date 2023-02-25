package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.serialization.SerialName

@Entity
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val Image: String,
    val category: String
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemRoom")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemRoom)

    @Insert
    fun saveMenuItem(menuItem: MenuItemRoom)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemRoom::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}