package com.sgupta.databaseinternalstorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Activity_b extends AppCompatActivity {

    private TextView mUsername;
    private TextView mPassword;
    private static final String mDefault ="default";
    private FileInputStream mFileInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        mUsername = (TextView) findViewById(R.id.usernameTextView2);
        mPassword = (TextView) findViewById(R.id.passwordTextView2);
    }

    public void load(View view){
        try {
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            mFileInputStream = openFileInput("MyFile.txt");
            while((read = mFileInputStream.read())!=-1) {
                stringBuffer.append((char) read);
            }
            mUsername.setText(stringBuffer.substring(0,stringBuffer.indexOf(" ")));
            mPassword.setText(stringBuffer.substring(stringBuffer.indexOf(" ")+1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"file not found",Toast.LENGTH_LONG).show();
            Log.d("Files",e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"read failed",Toast.LENGTH_LONG).show();
            Log.d("Files",e.toString());
        }finally {
            try {
                mFileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this,"file not closed",Toast.LENGTH_LONG).show();
                Log.d("Files",e.toString());
            }
        }
    }

    public void previous(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}