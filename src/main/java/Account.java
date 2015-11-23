import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AMANI CHRISTIAN CYRILLE ALVES on 27/10/2015.
 */

public  final class Account implements Answer
{
    public static String showNumberSMS(Token token)
    {
        String url = "https://api.orange.com/sms/admin/v1/contracts";
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static String showNumberSMS(Token token,int internationalCode)
    {
        String url = "https://api.orange.com/sms/admin/v1/contracts?country="+internationalCode;
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static String showSMSStatistics(Token token)
    {
        String url = "https://api.orange.com/sms/admin/v1/statistics";
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static String showSMSStatistics(Token token,int internationalCode)
    {
        String url = "\"https://api.orange.com/sms/admin/v1/statistics?country="+internationalCode;
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static String showSMSStatistics(Token token,String appId)
    {
        String url = "https://api.orange.com/sms/admin/v1/statistics?appid="+appId;
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static String showSMSStatistics(Token token,int internationalCode,String appId)
    {
        String url = "https://api.orange.com/sms/admin/v1/statistics?country="+internationalCode+"&appid="+appId;
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static String historicPurchase(Token token)
    {
        String url = "https://api.orange.com/sms/v1/purchaseorders";
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static String historicPurchase(Token token , int internationalCode)
    {
        String url = "https://api.orange.com/sms/v1/purchaseorders?country="+internationalCode;
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        HttpClient httpOrange = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        Header headerAuth = new Header("Authorization",token_type+" "+access_token);
        getMethod.addRequestHeader(headerAuth);
        return Answer.giveAnswer(httpOrange,getMethod);
    }
    public static JSONArray parseToJsonArray(String source)
    {
        JSONArray jsonArray = null;
        try
        {
            jsonArray = new JSONArray(source);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return jsonArray;
        }
    }
    public static JSONObject parseToJsonObject(String source)
    {
        JSONObject jsonObject = null;
        try
        {
            jsonObject = new JSONObject(source);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return jsonObject;
        }
    }
}
