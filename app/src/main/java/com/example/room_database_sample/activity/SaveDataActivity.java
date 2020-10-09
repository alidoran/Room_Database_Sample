package com.example.room_database_sample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.example.room_database_sample.R;
import com.example.room_database_sample.dao.AppDatabase;
import com.example.room_database_sample.model.Contact;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SaveDataActivity extends AppCompatActivity {

    private TextInputEditText edtUserName;
    private TextInputEditText edtLastName;
    private TextInputEditText edtPhoneNo;
    private MaterialButton btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        initView();
        initEvent();


    }

    private void initView() {
        edtUserName = findViewById(R.id.save_data_user_name_edt);
        edtLastName = findViewById(R.id.save_data_last_name_edt);
        edtPhoneNo = findViewById(R.id.save_data_phone_no_edt);
        btnAccept = findViewById(R.id.save_data_ok_btn);
    }

    private void initEvent() {
        btnAccept.setOnClickListener(v -> {
            int uid = -1;
            Contact insertedContact = createModel();
            List<Contact> contactList = AppDatabase.getInstance(this).contactDao().getAll();
            for (Contact contact : contactList) {
                if ((insertedContact.getFirstName().equals(contact.getFirstName()) &&
                        insertedContact.getLastName().equals(contact.getLastName())) ||
                        insertedContact.getPhoneNumber().equals(contact.getPhoneNumber())) {
                    uid = contact.getUid();
                    break;
                }
            }
            if (uid == -1)
                AppDatabase.getInstance(this).contactDao().insertAll(insertedContact);
            else {
                insertedContact.setUid(uid);
                AppDatabase.getInstance(this).contactDao().update(insertedContact);
            }
                Toast.makeText(this, contactList.get(0).getFirstName(), Toast.LENGTH_SHORT).show();
        });
    }

    private Contact createModel() {
        Contact contact = new Contact();
        contact.setFirstName(edtUserName.getText().toString());
        contact.setLastName(edtLastName.getText().toString());
        contact.setPhoneNumber(edtPhoneNo.getText().toString());
        return contact;
    }
}