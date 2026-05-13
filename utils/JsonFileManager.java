package com.iridiscense.unitoperations.utils;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by: HR Bon Con.
 * Date: 14,June,2019
 */
public class JsonFileManager {

    private static WriteObjectFile file;
    private JSONObject object;
    private static final JsonFileManager ourInstance = new JsonFileManager();

    private JsonFileManager() {
        if(object==null){
            object = new JSONObject();
        }
    }

    public static JsonFileManager getInstance(Context context) {
        file = new WriteObjectFile(context);
        return ourInstance;
    }

    private void createJsonFile(String pathFile, String fileName) {
        file.createObjectFile(pathFile,fileName);
    }

    private void readJsonFile(String fileName) {
        file.readObject(fileName);
    }

    private void writeJsonFile(Object input, String fileName) {
        file.writeObject(input, fileName);
    }

    private void deleteFile(String pathFile, String fileName) {

       
    }
}
