/**
 * Created by AMANI CHRISTIAN CYRILLE ALVES on 24/10/2015.
 */

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;

import org.json.*;

import java.util.Base64;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Token
{
    private String tokenType;
    private String accessToken;
    private long second;

    private Token(String tokenType, String accessToken,long second)
    {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.second = second;
    }

    public static Token generateToken(String id, String codeSecret)
    {
        String tokenJson = null;
        String url = "https://api.orange.com/oauth/v2/token";
        String encodeKey = null;
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] key = (id+":"+codeSecret).getBytes();
        encodeKey = encoder.encodeToString(key);
        Header header = new Header("Authorization","Basic "+encodeKey);
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader(header);
        postMethod.setParameter("grant_type","client_credentials");
        HttpClient httpOrange = new HttpClient();
        tokenJson = giveAnswer(httpOrange, postMethod);
        return  init(tokenJson);
    }

    private static String giveAnswer(HttpClient httpClient, PostMethod postMethod)
    {
        String answer = "";
        BufferedReader reader = null;
        InputStreamReader inputStream = null;
        try
        {
            int status =  httpClient.executeMethod(postMethod);
            if(status == HttpStatus.SC_CREATED)
            {
                inputStream = new InputStreamReader(postMethod.getResponseBodyAsStream());
                reader =  new BufferedReader(inputStream);
                String ans;
                while((ans = reader.readLine()) != null)
                {
                    answer += ans;
                    System.out.println(answer);
                }
            }
            else if(status == HttpStatus.SC_UNAUTHORIZED)
                System.err.println("error authorisation");
            else
                System.err.println("error "+ status);
        }
        catch (HttpException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            postMethod.releaseConnection();
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            if(inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }

    private static Token init(String source)
    {
        Token token = null;
        JSONObject jsonObject = null;
        try
        {
            jsonObject = new JSONObject(source);
            String token_type = jsonObject.getString("token_type");
            String access_token = jsonObject.getString("access_token");
            long expires_in = jsonObject.getLong("expires_in");
            token = new Token(token_type,access_token,expires_in);
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return token;
        }
    }

    public String getTokenType()
    {
        return this.tokenType;
    }
    public String getAccessToken()
    {
        return this.accessToken;
    }
    public long getSecond()
    {
        return this.second;
    }
}
