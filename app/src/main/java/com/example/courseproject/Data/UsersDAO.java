package com.example.courseproject.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersDAO {

    @Query("SELECT * FROM Users")
    List<Users> getAll();

    @Insert
    void  insert(Users users);

    @Query("SELECT * FROM users WHERE bloodType = :bloodType")
    List<Users> getB(String bloodType);
}
