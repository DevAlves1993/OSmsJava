/**
 * Created by AMANI CHRISTIAN CYRILLE ALVES on 24/10/2015.
 */


public class Token
{
    private String tokenType;
    private String accessToken;
    private long second;

    public Token(String tokenType, String accessToken, long second)
    {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.second = second;
    }

    public String getTokenType()
    {
        return tokenType;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public long getSecond()
    {
        return second;
    }
}