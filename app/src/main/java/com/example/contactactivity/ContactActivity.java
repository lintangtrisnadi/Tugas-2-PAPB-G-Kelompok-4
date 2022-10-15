package com.example.contactactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView option;
    private LinearLayout layAddContact;
    private EditText etName, etNumber, etInstagram, etGroup;
    private Button btnClear, btnSubmit;

    private ArrayList<ContactModel> contactList = new ArrayList<>();
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        recyclerView = findViewById(R.id.recycle_contact);
        recyclerView.setHasFixedSize(true);

        layAddContact = findViewById(R.id.layout_add);
        option = findViewById(R.id.tv_option);
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        etInstagram = findViewById(R.id.et_instagram);
        etGroup = findViewById(R.id.et_group);
        btnClear = findViewById(R.id.btn_clear);
        btnSubmit = findViewById(R.id.btn_submit);

        option.setOnClickListener(v -> {
            if (recyclerView.getVisibility() == View.VISIBLE){
                recyclerView.setVisibility(View.GONE);
                layAddContact.setVisibility(View.VISIBLE);
                clearData();

            }else {
                recyclerView.setVisibility(View.VISIBLE);
                layAddContact.setVisibility(View.GONE);
            }
        });

        btnClear.setOnClickListener(v -> {
            clearData();
        });

        btnSubmit.setOnClickListener(v -> {
            if (etName.getText().toString().equals("") ||
                    etNumber.getText().toString().equals("") ||
                    etInstagram.getText().toString().equals("") ||
                    etGroup.getText().toString().equals("") ){
                Toast.makeText(this, "Harap mengisi semua form", Toast.LENGTH_SHORT).show();
            } else {
                contactList.add(new ContactModel(etName.getText().toString(), etNumber.getText().toString(), etGroup.getText().toString(), etInstagram.getText().toString()));
                contactAdapter = new ContactAdapter(this, contactList);
                recyclerView.setAdapter(contactAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                layAddContact.setVisibility(View.GONE);
            }
        });

        contactList.add(new ContactModel("Anggraeni Puspa", "+6287852789", "bussines", "angg123"));
        contactList.add(new ContactModel("Sari Rahmawati", "+628785566049", "family", "sarwi"));
        contactList.add(new ContactModel("Megananda Dwi", "+628785541209", "study", "megmeg"));
        contactList.add(new ContactModel("Lintang Trisnadi", "+62878785505", "family", "lin77"));
        contactList.add(new ContactModel("Kiki Kaka", "+628785765243", "friend", "ki90"));

        contactAdapter = new ContactAdapter(this, contactList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ContactActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter.setOnItemClickListener((position, v) -> {
            contactList.remove(position);
            contactAdapter = new ContactAdapter(this, contactList);
            recyclerView.setAdapter(contactAdapter);
        });
        recyclerView.setAdapter(contactAdapter);
    }

    public void clearData(){
        etName.setText("");
        etNumber.setText("");
        etInstagram.setText("");
        etGroup.setText("");
    }
}