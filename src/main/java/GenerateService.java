/**
 * Created by AMANI on 28/11/2015.
 */

import java.util.Base64;
import com.squareup.okhttp.Request;

public class GenerateService
{
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LOCATION = "Location";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String DATE = "Date";

    private final String END_POINT_AUTH = "https://api.orange.com/oauth/token";
    private final String END_POINT_REMAINDER = "https://api.orange.com/sms/admin/v1/contracts";
    private final String END_POINT_STATISTICS = "https://api.orange.com/sms/admin/v1/statistics";
    private final String END_POINT_HISTORIC = "https://api.orange.com/sms/admin/v1/purchaseorders";


    private String id;
    private String secretCode;
    private String encodedCode;

    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        encodedCode = id+":"+secretCode;
        encode();
    }
    private void encode()
    {
        Base64.Encoder encoder = Base64.getEncoder();
        encodedCode = encoder.encodeToString(encodedCode.getBytes());
    }

    public void getToken()
    {

    }
}
