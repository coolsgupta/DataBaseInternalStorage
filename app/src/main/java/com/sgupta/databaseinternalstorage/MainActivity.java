package com.sgupta.databaseinternalstorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private FileOutputStream mFileOutputStream;
    private File mFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = (EditText) findViewById(R.id.usernameEditText);
        mPassword = (EditText) findViewById(R.id.passwordEditText);

    }

    public void save(View view){
        String username = mUsername.getText().toString()+ " ";
        String password = mPassword.getText().toString();
        try {
            mFile = getFilesDir();
            mFileOutputStream = openFileOutput("MyFile.txt", Context.MODE_PRIVATE);
            mFileOutputStream.write(username.getBytes());
            mFileOutputStream.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"file not created",Toast.LENGTH_LONG).show();
            Log.d("Files",e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"input error",Toast.LENGTH_LONG).show();
            Log.d("Files",e.toString());
        } finally {
            try {
                mFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this,"not closed",Toast.LENGTH_LONG).show();
                Log.d("Files",e.toString());
            }
        }

        Toast.makeText(this,"data saved at "+mFile+"\\MyFile",Toast.LENGTH_LONG).show();
    }
    public void next(View view){
        Intent intent = new Intent(this,Activity_b.class);
        startActivity(intent);
    }
}
