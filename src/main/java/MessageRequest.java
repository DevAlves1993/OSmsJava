/**
 * Created by AMANI CHRISTIAN CYRILLE ALVES on 26/10/2015.
 */

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageRequest
{
    private String address;
    private String senderAddress;
    private String message;
    private Token token;
    private final String CONTENT_TYPE = "application/json";

    public MessageRequest(String address,String senderAddress,String message,Token token)
    {
        this.address = address;
        this.senderAddress = senderAddress;
        this.message = message;
        this.token = token;
    }

    public MessageRequest(String address,String senderAddress,String message)
    {
        this.address = address;
        this.senderAddress = senderAddress;
        this.message = message;
       // this.token = token;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setSenderAddress()
    {
        this.senderAddress = senderAddress;
    }
    public void setToken(Token token)
    {
        this.token = token;
    }
    public String getAddress()
    {
        return this.address;
    }
    public String getSenderAddress()
    {
        return this.senderAddress;
    }
    public Token getToken()
    {
        return this.token;
    }

    public String encodeSenderAddress()
    {
        Pattern pattern = Pattern.compile(":");
        Matcher isMatcher = pattern.matcher(senderAddress);
        return isMatcher.replaceAll("%3A%2B"); // equivalent : see method String replace(String Reg, String replacement)
    }

    public String createJSon()
    {
        String result = null;
        try
        {
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("message",message);
            JSONObject jsonAddress = new JSONObject();
            jsonAddress.put("address",address);
            jsonAddress.put("senderAddress",senderAddress);
            jsonAddress.put("outboundSMSTextMessage",jsonMessage);
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("outboundSMSMessageRequest",jsonAddress);
            result = jsonRequest.toString();
        }
        catch (JSONException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    public String send()
    {
        String jsonString = createJSon();
        String token_type = token.getTokenType();
        String access_token = token.getAccessToken();
        String url = "https::/api.orange.com/smsmessaging/v1/outbound/"+ encodeSenderAddress()+"/requests";
        HttpClient httpOrange = new HttpClient();
        Header headerAuthorization = new Header("Authorization",token_type+" "+access_token);
        Header headerContentType = new Header("Content-Type",CONTENT_TYPE);
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader(headerAuthorization);
        postMethod.addRequestHeader(headerContentType);
        postMethod.addParameter("",jsonString);
        return  Answer.giveAnswer(httpOrange,postMethod);
    }


}
