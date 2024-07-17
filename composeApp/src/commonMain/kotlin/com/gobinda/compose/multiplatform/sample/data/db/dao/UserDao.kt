package com.gobinda.compose.multiplatform.sample.data.db.dao

import androidx.room.*
import com.gobinda.compose.multiplatform.sample.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun observeUser(): Flow<List<User>>

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Query("SELECT * from users where email = :email LIMIT 1")
    fun loadUserByEmail(email: String): Flow<User>

    @Query("SELECT * from users where email = :email and password = :pass LIMIT 1")
    fun loadUser(email: String, pass : String): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<User>)

    @Query("DELETE FROM users")
    suspend fun deleteUser()

    @Insert
    suspend fun insertUser(user: User): Long

}

