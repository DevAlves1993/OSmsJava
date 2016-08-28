package org.akanza;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Created by user on 28/08/2016.
 */
public interface Resource
{
    String CONTENT_TYPE = "Content-Type";
    String LOCATION = "Location";
    String CONTENT_LENGTH = "Content-Length";
    String DATE = "Date";

    MediaType jsonMedia = MediaType.parse("application/json;charset=utf-8");

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();
}
