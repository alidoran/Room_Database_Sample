package com.example.room_database_sample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.room_database_sample.R;
import com.example.room_database_sample.dao.AppDatabase;
import com.example.room_database_sample.model.Contact;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MaterialButton saveDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView() {
        saveDataBtn = findViewById(R.id.main_save_data_btn);
    }

    private void initEvent(){
        saveDataBtn.setOnClickListener(v->{
            Intent intent = new Intent(this , SaveDataActivity.class);
            startActivity(intent);
        });
    }

    private Contact createSampleContacts() {
        Contact contact = new Contact();
        contact.setFirstName("ali");
        contact.setLastName("doran");
        contact.setPhoneNumber("+989124955481");
        return contact;
    }
}