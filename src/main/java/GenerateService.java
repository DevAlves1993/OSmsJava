
/**
 * Created by AMANI on 28/11/2015.
 */

import java.io.IOException;
import java.util.Base64;

import com.squareup.okhttp.*;
import com.google.gson.Gson;


import Error.ServiceException;


public class GenerateService
{
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LOCATION = "Location";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String DATE = "Date";

    private final String END_POINT_AUTH = "https://api.orange.com/oauth/v2/token";
    private final String END_POINT_REMAINDER = "https://api.orange.com/sms/admin/v1/contracts";
    private final String END_POINT_STATISTICS = "https://api.orange.com/sms/admin/v1/statistics";
    private final String END_POINT_HISTORIC = "https://api.orange.com/sms/admin/v1/purchaseorders";
    private final String AUTHORIZATION = "Authorization";

    private static final MediaType typeJson = MediaType.parse("application/json;charset=utf-8");;
    private static final RequestBody formBody = new FormEncodingBuilder()
                                                .add("grant_type","client_credentials")
                                                .build();

    private String id;
    private String secretCode;
    private String encodedCodeBasic;
    private OkHttpClient client;
    private Response response;
    private Gson gson;

    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        encode();
        this.gson = new Gson();
        client = new OkHttpClient();
    }
    private void encode()
    {
       encodedCodeBasic = Credentials.basic(id,secretCode);
    }

    public Token getToken() throws IOException, ServiceException
    {
        Request request = new Request.Builder()
                            .url(END_POINT_AUTH)
                            .post(formBody)
                            .addHeader(AUTHORIZATION,encodedCodeBasic)
                            .build();
        response = client.newCall(request).execute();
        if(response.isSuccessful())
        {
            Token token = gson.fromJson(response.body().charStream(),Token.class);
            return token;
        }
        else
        {
            throw new ServiceException(response.body().string());
        }
    }
    public void sendSMS(Token token) throws ServiceException
    {

    }

}
