package com.iridiscense.unitoperations.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * From: https://gist.github.com/whitfin/8676531
 *
 * You need to add implements Serializable to you Object class.
 *
 * Allows the reading/writing of an object from/to a file
 * inside the application's /data directory. Allows for
 * more efficient storing of objects such as arrays instead
 * of within SharedPreferences
 *
 * @author Isaac Whitfield
 * @version 31/07/2013
 */
public class WriteObjectFile {

    private Context context;
    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private Object outputObject;
    private String filePath;

    public WriteObjectFile(Context c){
        context = c;
    }

    public Object readObject(String fileName){
        try {
            filePath = context.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + fileName;
            fileIn = new FileInputStream(filePath);
            objectIn = new ObjectInputStream(fileIn);
            outputObject = objectIn.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputObject;
    }

    public void writeObject(Object inputObject, String fileName){
        try {
            filePath = context.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + fileName;
            fileOut = new FileOutputStream(filePath);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(inputObject);
            fileOut.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //addded
    public void createObjectFile(String fileDir, String fileName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(context, "Need SdCard", Toast.LENGTH_SHORT).show();
        } else {
            String dir = Environment.getExternalStorageDirectory()+File.separator+fileDir;
            //create folder
            File folder = new File(dir); //folder name
            folder.mkdirs();

            //create file
            File file = new File(dir, "myTabs.json");
            try {
                if(file.createNewFile()){
                    Toast.makeText(context, "File Created", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "No file :( o ya existe", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}