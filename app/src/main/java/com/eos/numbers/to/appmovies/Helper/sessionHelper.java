package com.eos.numbers.to.appmovies.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class sessionHelper {
    private Context context;
    private SharedPreferences sessioData;
    private SharedPreferences.Editor editor;
    private static final String PREFERENCES_FILE = "movies_series";
    public static final String LANGUAGE = "language";
    public static final String CATEGORY = "category";
    public static final String MEDIA = "media";
    public static final String APYKEY = "key";

    public sessionHelper(Context context){
        this.context = context;
        sessioData = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public void setLanguage(String language){
        editor = sessioData.edit();
        editor.putString(LANGUAGE,language);
        editor.apply();
    }

    public void setCategory(String category){
        editor = sessioData.edit();
        editor.putString(CATEGORY,category);
        editor.apply();
    }

    public void setMedia(String media){
        editor = sessioData.edit();
        editor.putString(MEDIA,media);
        editor.apply();
    }

    public void setApykey(String key){
        editor = sessioData.edit();
        editor.putString(APYKEY,key);
        editor.apply();
    }

    public String getLanguage() {
        return sessioData.getString(LANGUAGE,"");
    }

    public String getCategory() {
        return sessioData.getString(CATEGORY,"");
    }

    public String getMedia() {
        return sessioData.getString(MEDIA,"");
    }

    public String getApykey() {
        return sessioData.getString(APYKEY,"");
    }

}
