package com.arrowwould.periodtracker.Utils;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;


public class JsonHelper {
    private static final String TAG = "JsonHelper";

    public static List<HashMap<String, Object>> getJsonData(Context context, String str) throws IOException {
        InputStream open = context.getAssets().open(str);
        InputStreamReader inputStreamReader = new InputStreamReader(open);
        List<HashMap<String, Object>> list = (List) new Gson().fromJson(inputStreamReader, new TypeToken<List<HashMap<String, Object>>>() { 
        }.getType());
        inputStreamReader.close();
        open.close();
        return list;
    }
}
