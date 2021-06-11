package com.example.passwordmanager

import androidx.room.*

@Dao
interface AccountsDataDao {


    @Query("SELECT * FROM accounts_table WHERE website_name LIKE :first AND " +
            "user_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): AccountData


    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertAll(vararg accountData: AccountData)

    @Delete
    fun delete(accountData: AccountData)


}
