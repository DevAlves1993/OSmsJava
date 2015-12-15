import java.io.IOException;
import Error.ServiceException;
/**
 * Created by AMANI Christian on 28/11/2015.
 */
public class Main
{
    public static void main(String[] args)
    {
        GenerateService service = new GenerateService("353","hdbhzbd");
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
