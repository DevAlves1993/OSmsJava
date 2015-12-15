
/**
 * Created by AMANI on 28/11/2015.
 */

import java.io.IOException;
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
    private String jsonBody;

    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        encode();
        this.gson = new Gson();
        client = new OkHttpClient();
        jsonBody = null;
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
            throw new ServiceException(response.body().string());
    }
    public ResponseSMS sendSMS(Token token,SMS sms) throws ServiceException, IOException
    {
        ResponseSMS responseSms = null;
        jsonBody = gson.toJson(sms);
        String senderAddress = encodedSenderAddress(sms.getOutBoundSMSMessageRequest()
                                                    .getSenderAddress());
        String url = createEndPointSms(senderAddress);
        RequestBody bodySms = RequestBody.create(typeJson, jsonBody);
        Request request = new Request.Builder()
                            .url(url)
                            .post(bodySms)
                            .addHeader(AUTHORIZATION,token.createAccess())
                            .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful())
        {
            responseSms = gson.fromJson(response.body().charStream(),ResponseSMS.class);
            return responseSms;
        }
        else
            throw new ServiceException(response.body().string());
    }
    private String encodedSenderAddress(String number)
    {
        return number.replaceAll(":\\+","%3A%2B");
    }
    private String createEndPointSms(String senderAddress)
    {
        String url = "https://api.orange.com/smsmessaging/v1/outbound/"+senderAddress+"/requests";
        return url;
    }
    public StatisticSMS statisticSMS(Token token) throws IOException,ServiceException
    {
        StatisticSMS statisticSms = null;
        Request request = new Request.Builder()
                            .url(END_POINT_STATISTICS)
                            .addHeader(AUTHORIZATION,token.createAccess())
                            .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful())
        {
            statisticSms = gson.fromJson(response.body().charStream(),StatisticSMS.class);
            return statisticSms;
        }
        else
            throw new ServiceException(response.body().string());
    }
    public RemainderSMS remainderSMS(Token token) throws IOException,ServiceException
    {
        RemainderSMS remainderSms = null;
        Request request = new Request.Builder()
                            .url(END_POINT_REMAINDER)
                            .addHeader(AUTHORIZATION,token.createAccess())
                            .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful())
        {
            remainderSms = gson.fromJson(response.body().charStream(),RemainderSMS.class);
            return remainderSms;
        }
        else
            throw new ServiceException(response.body().string());
    }
    public HistoricPurchase obtainHistoric(Token token) throws IOException,ServiceException
    {
        HistoricPurchase historicpurchase = null;
        Request request = new Request.Builder()
                            .url(END_POINT_HISTORIC)
                            .addHeader(AUTHORIZATION,token.createAccess())
                            .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful())
        {
            historicpurchase = gson.fromJson(response.body().charStream(),HistoricPurchase.class);
            return historicpurchase;
        }
        else
            throw new ServiceException(response.body().string());
    }
}
