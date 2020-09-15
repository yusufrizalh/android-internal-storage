package id.yusufrizalh.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

public class DisplayActivity extends AppCompatActivity {
    private TextView txtName, txtPassword;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        txtName = findViewById(R.id.txtName);
        txtPassword = findViewById(R.id.txtPassword);
        btnLoad = findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput("Data.txt");    // membaca
                    int read = -1;
                    StringBuffer buffer = new StringBuffer();
                    while ((read = fileInputStream.read()) != -1) {
                        buffer.append((char)read);
                    }
                    Log.d("Code", buffer.toString());   // tampil di console
                    String name, password;
                    name = buffer.substring(0, buffer.indexOf(" "));
                    password = buffer.substring(buffer.indexOf(" ") + 1);
                    txtName.setText(name);
                    txtPassword.setText(password);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Toast.makeText(DisplayActivity.this, "Data loaded!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DisplayActivity.this, MainActivity.class));
        finish();
    }
}