package com.example.passwordmanager
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="accounts_table")
class AccountData() {
    @PrimaryKey (autoGenerate = true) var id=0
    @ColumnInfo(name = "website_name")
    lateinit var webite:String
    @ColumnInfo(name = "user_name")
    lateinit var username:String
    @ColumnInfo(name = "password")
    lateinit var pass:String
}