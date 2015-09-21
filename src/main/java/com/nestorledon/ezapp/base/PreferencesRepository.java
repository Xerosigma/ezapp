package com.nestorledon.ezapp.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by nestorledon on 4/3/15.
 */
public class PreferencesRepository {

    private final static String TAG = PreferencesRepository.class.getSimpleName();

    /**
     * Saves data to preferences as JSON string.
     * @param context
     * @param object
     * @param fileName
     * @param dataKey
     * @param <T>
     * @return the saved object. Null if failed.
     */
    public static <T> T setData(Context context, Type type, String fileName, String dataKey, T object) {

        final Gson gson = new Gson();
        final SharedPreferences prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        String json = gson.toJson(object);

        editor.putString(dataKey, json);
        editor.commit();

        return (T) getData(context, type, fileName, dataKey);
    }

    /**
     * Retrieves data from preferences.
     * @param context
     * @param type
     * @param fileName
     * @param dataKey
     * @param <T>
     * @return the object form settings.
     */
    public static <T> T getData(Context context, Type type, String fileName, String dataKey) {

        final Gson gson = new Gson();
        final SharedPreferences prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        String savedJson = prefs.getString(dataKey, "");
        T data = gson.fromJson(savedJson, type);
        return data;
    }
}
