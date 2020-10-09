package com.example.room_database_sample.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.room_database_sample.model.Contact;

import java.net.ContentHandler;

@Database(entities = {Contact.class}, version = 1)

    public abstract class AppDatabase extends RoomDatabase {
        private static AppDatabase appDatabase;
        public abstract contactDao.ContactDao contactDao();

        public static AppDatabase getInstance(Context context){
            if (appDatabase == null)
                appDatabase =Room.databaseBuilder(context,AppDatabase.class, "room_database").allowMainThreadQueries().build();
            return appDatabase;
        }

}
