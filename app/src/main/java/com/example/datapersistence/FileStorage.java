package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorage extends AppCompatActivity {

    private static final String FILE_INTERN_NAME = "inputData.txt";
    private static final Object FILE_EXTERN_NAME = "externData.txt";
    EditText inputAgeInterne;
    EditText inputAgeExterne;
    EditText inputNameInterne;
    EditText inputNameExterne;
    TextView showMessage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        //Intern file storage
        inputAgeInterne = findViewById(R.id.InputAgeInterne);
        inputNameInterne = findViewById(R.id.InputNameInterne);

        //Extern file storage
        inputAgeExterne = findViewById(R.id.InputAgeExterne);
        inputNameExterne = findViewById(R.id.InputNameExterne);

        //show result
        showMessage = findViewById(R.id.showMessage);
    }

    public void saveInterne(View view) throws IOException {
        FileOutputStream fos = openFileOutput(FILE_INTERN_NAME, MODE_PRIVATE);
        String name = inputNameInterne.getText().toString();
        String age = inputAgeInterne.getText().toString();
        String messageInterne = "My name is:" + name + ". I have :"+ age;
        fos.write(messageInterne.getBytes());
        fos.close();
    }

    public void readInterne(View view) throws IOException{
        FileInputStream fis =  openFileInput(FILE_INTERN_NAME);
        StringBuffer buffer= new StringBuffer();
        int content;
        while((content = fis.read()) != -1) {
            buffer.append((char) content);
        }
        showMessage.setText(buffer);
        fis.close();
    }

    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void saveExterne(View view) throws IOException{
        if (isExternalStorageWritable()) {
            File directory = this.getExternalFilesDir(null);
            File fileExterne = new File(directory + "/" + FILE_EXTERN_NAME);
            FileOutputStream fos = new FileOutputStream(fileExterne);
            String name = inputNameExterne.getText().toString();
            String age = inputAgeExterne.getText().toString();
            String messageInterne = "My name is:" + name + ". I have :"+ age;
            fos.write(messageInterne.getBytes());
            fos.close();
        }
    }

    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    public void readExterne(View view) throws IOException {
        if (isExternalStorageReadable()) {
            File directory = this.getExternalFilesDir(null);
            File fileExterne = new File(directory + "/" + FILE_EXTERN_NAME);
            if (fileExterne.exists()) {
                FileInputStream fis =  new FileInputStream(fileExterne);
                StringBuffer buffer= new StringBuffer();
                int content;
                while((content = fis.read()) != -1) {
                    buffer.append((char) content);
                }
                showMessage.setText(buffer);
                fis.close();
            }
        }
    }
}