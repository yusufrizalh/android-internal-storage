package id.yusufrizalh.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // (1) instance variable
    private EditText editName, editPassword;
    private Button btnSave, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // (2) mengenali semua komponen
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        btnSave = findViewById(R.id.btnSave);
        btnNext = findViewById(R.id.btnNext);

        // (3) event handling
        btnSave.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            File file = null;
            String name = editName.getText().toString();
            String password = editPassword.getText().toString();

            FileOutputStream fileOutputStream = null;   // menulis
            try {
                name = name + " ";
                file = getFilesDir();   // persistence
                fileOutputStream = openFileOutput("Data.txt", Context.MODE_PRIVATE);
                fileOutputStream.write(name.getBytes());
                fileOutputStream.write(password.getBytes());
                Toast.makeText(this, "Data saved! \n" +
                        "Path: " + file + "\t Data.txt", Toast.LENGTH_SHORT).show();
                editName.setText("");
                editPassword.setText("");
                return;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } else if (v.getId() == R.id.btnNext) {
            Toast.makeText(this, "Next Activity", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(this, DisplayActivity.class);
            startActivity(myIntent);
        }
    }
}