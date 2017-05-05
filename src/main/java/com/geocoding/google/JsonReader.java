package com.geocoding.google;

import com.google.common.base.Joiner;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Den on 3/31/2017.
 */
public class JsonReader {

    public JSONObject readJson(final String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            final String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private  String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    public  String encodeParams(final Map<String, String> params) {
        return Joiner.on('&').join(params.entrySet().stream().map(input -> {
            try {
                return input.getKey() + '=' + URLEncoder.encode(input.getValue(), "utf-8");
            } catch (final UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()));
    }
}


