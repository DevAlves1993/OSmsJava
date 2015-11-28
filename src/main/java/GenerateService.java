/**
 * Created by AMANI on 28/11/2015.
 */

import java.util.Base64;

import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.OkHeaders;


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
    private final String CREDENTIALS = "grant_type=client_credentials";

    private static final MediaType typeCredential;
    static
    {
        typeCredential = MediaType.parse("text;charset=utf-8");
    }

    private String id;
    private String secretCode;
    private String encodedCodeBasic;
    private String encodeCodeBearer;
    private OkHttpClient client;

    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        encode();
        encodedCodeBasic ="Basic "+encodedCodeBasic;
        encodeCodeBearer = "Bearer "+encodeCodeBearer;
    }
    private void encode()
    {
        Base64.Encoder encoder = Base64.getEncoder();
        encodedCodeBasic = id+":"+secretCode;
        encodedCodeBasic = encoder.encodeToString(encodedCodeBasic.getBytes());
        encodeCodeBearer = encodedCodeBasic;
    }

    public void getToken()
    {
        RequestBody body = RequestBody.create(typeCredential,CREDENTIALS);
        Request request = new Request.Builder()
                            .url(END_POINT_AUTH)
                            .post(body)
                            .addHeader(AUTHORIZATION,encodedCodeBasic)
                            .build();
    }
}
