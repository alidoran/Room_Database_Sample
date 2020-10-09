package com.example.room_database_sample.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room_database_sample.model.Contact;

import java.util.List;

public class contactDao {
    @Dao
    public interface ContactDao {
        @Query("SELECT * FROM contact")
        List<Contact> getAll();

        @Query("SELECT * FROM contact WHERE uid IN (:contactIds)")
        List<Contact> loadAllByIds(int[] contactIds);

        @Query("SELECT * FROM contact WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1")
        Contact findByName(String first, String last);

        @Query("SELECT * FROM contact WHERE phone_number LIKE :phoneNumber LIMIT 1")
        Contact findByPhone(String phoneNumber);

        @Insert
        List<Long> insertAll(Contact... contacts);

        @Update
        void update(Contact... contacts);

        @Delete
        void delete(Contact contact);
    }
}
