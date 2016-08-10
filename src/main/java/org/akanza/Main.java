package org.akanza;

import java.io.IOException;
import org.akanza.error.ServiceException;
/**
 * Created by AMANI Christian on 28/11/2015.
 */
public class Main
{
    public static void main(String[] args)
    {
        ServiceSMS service = new ServiceSMS("353","hdbhzbd");
        try
        {
            Token token  = service.getToken();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

    }
}
