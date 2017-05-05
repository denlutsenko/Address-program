package com.geocoding.google;

import com.entity.StoreAddress;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by lutsenko.d on 10.04.2017.
 */
public class GeoCoding {
    private static final Logger LOG = Logger.getLogger(GeoCoding.class);
    private JsonReader jsonReader;
    private final String BASE_URL = "http://maps.googleapis.com/maps/api/geocode/json";


    public GeoCoding() {
        jsonReader = new JsonReader();
    }

    public List<StoreAddress> findCoordinates(List<StoreAddress> tmpAddresses) {

        for (StoreAddress address : tmpAddresses) {
            final Map<String, String> params = Maps.newHashMap();
            params.put("sensor", "false");
            params.put("address", address.getStoreAddress());
            final String url = BASE_URL + '?' + jsonReader.encodeParams(params);
            try {
                JSONObject response = jsonReader.readJson(url);
                JSONObject location = response.getJSONArray("results").getJSONObject(0);
                location = location.getJSONObject("geometry");
                location = location.getJSONObject("location");
                double longitude = location.getDouble("lng");
                double latitude = location.getDouble("lat");
                address.setLatitude(latitude);
                address.setLongitude(longitude);
            } catch (JSONException | IOException e) {
                LOG.error("can't find GPS coordinates, " + e + "\n"
                         + "address TT: " + address.getStoreAddress() + "\n");
            }
        }
        return tmpAddresses;
    }
}
