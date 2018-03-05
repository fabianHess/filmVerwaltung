package hess.fabian.filmverwaltung.tmdbApi;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by Fabian on 04.03.2018.
 */

public class JsonReader {

    private JSONObject jsonObject;

    public JsonReader(String url) throws IOException, JSONException {
        jsonObject = readJsonFormat(url);
    }

    public JSONObject readJsonFormat(String url) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        InputStream inputStream = null;
        try {
            URL movieUrl = new URL(url);
            URLConnection urlConnection = movieUrl.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);
            JSONObject jsonObject = new JSONObject(jsonText);

            return jsonObject;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String readAll(Reader reader) {
        StringBuilder stringBuilder = new StringBuilder();
        int cp = -1;
        do {
            try {
                cp = reader.read();
                stringBuilder.append((char) cp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(cp != -1);

        return stringBuilder.toString();
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
