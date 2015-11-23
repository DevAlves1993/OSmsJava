import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by AMANI CHRISTIAN CYRILLE ALVES  on 26/10/2015.
 */
public interface Answer
{
    public static String giveAnswer(HttpClient httpClient, PostMethod postMethod)
    {
            String answer = "";
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader = null;
            try
            {
                int result =  httpClient.executeMethod(postMethod);
                switch (result)
                {
                    case HttpStatus.SC_CREATED:
                    {
                        inputStreamReader = new InputStreamReader(postMethod.getResponseBodyAsStream());
                        bufferedReader = new BufferedReader(inputStreamReader);
                        String ans;
                        while((ans = bufferedReader.readLine()) != null)
                        {
                            answer += ans;
                            System.out.println(answer);
                        }
                    }
                    case HttpStatus.SC_BAD_REQUEST :
                        System.err.println("error: "+result+" "+"BAD_REQUEST");
                    case HttpStatus.SC_UNAUTHORIZED:
                        System.err.println("error: " + result + "UNAUTHORIZED");
                    case HttpStatus.SC_NOT_FOUND:
                        System.err.println("error: " + result + "NOT_FOUND");
                    default:
                        System.err.println("error: " + result);
                }
            }
            catch(HttpException ex)
            {
                ex.printStackTrace();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                postMethod.releaseConnection();
                if (bufferedReader != null)
                {
                    try
                    {
                        bufferedReader.close();
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                if (inputStreamReader != null)
                {
                    try
                    {
                        inputStreamReader.close();
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                return answer;
            }

    }
    public static String giveAnswer(HttpClient httpClient,GetMethod getMethod)
    {
        String answer = "";
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try
        {
            int result =  httpClient.executeMethod(getMethod);
            switch (result)
            {
                case HttpStatus.SC_CREATED:
                {
                    inputStreamReader = new InputStreamReader(getMethod.getResponseBodyAsStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    String ans;
                    while((ans = bufferedReader.readLine()) != null)
                    {
                        answer += ans;
                        System.out.println(answer);
                    }
                }
                case HttpStatus.SC_BAD_REQUEST :
                    System.err.println("error: "+result+" "+"BAD_REQUEST");
                case HttpStatus.SC_UNAUTHORIZED:
                    System.err.println("error: " + result + "UNAUTHORIZED");
                case HttpStatus.SC_NOT_FOUND:
                    System.err.println("error: " + result + "NOT_FOUND");
                default:
                    System.err.println("error: " + result);
            }
        }
        catch(HttpException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            getMethod.releaseConnection();
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            if (inputStreamReader != null)
            {
                try
                {
                    inputStreamReader.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            return answer;
        }
    }
}
