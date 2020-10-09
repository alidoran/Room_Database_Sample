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
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class LoadDataActivity extends AppCompatActivity {

    private LinearLayoutCompat layUserName;
    private LinearLayoutCompat layLastName;
    private LinearLayoutCompat layPhoneNo;
    private MaterialTextView txtUserName;
    private MaterialTextView txtLastName;
    private TextInputEditText edtPhoneNo;
    private MaterialButton btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        initView();
        initEvent();
    }

    private void initView() {
        txtUserName = findViewById(R.id.load_data_user_name_txt);
        txtLastName = findViewById(R.id.load_data_last_name_txt);
        layPhoneNo = findViewById(R.id.load_data_phone_no_lay);
        edtPhoneNo = findViewById(R.id.load_data_phone_no_edt);
        btnAccept = findViewById(R.id.load_data_ok_btn);
    }

    private void initEvent() {
        layUserName.setOnFocusChangeListener((view, b) -> {
            if (edtPhoneNo == null){
                Toast.makeText(this, "Fill Phone Number", Toast.LENGTH_SHORT).show();
            }else{
                String phoneNumber = edtPhoneNo.getText().toString();
                List<Contact> contactList = (List<Contact>) AppDatabase.getInstance(this).contactDao().findByPhone(phoneNumber);
                if (contactList.size() == 0)
                    Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                else if (contactList.size() > 1)
                    Toast.makeText(this, "Dublicate phone", Toast.LENGTH_SHORT).show();
                else{
                    txtUserName.setText(contactList.get(0).getFirstName());
                    txtLastName.setText(contactList.get(0).getLastName());
                }
            }
        });
    }




}