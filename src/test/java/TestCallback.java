import okhttp3.FormBody;
import org.junit.Test;

/**
 * Created by AMANI on 10/08/2016.
 */
public class TestCallback
{
    @Test
    public void testCallback() throws Exception
    {
        FormBody formBody = new FormBody.Builder()
                .add("grant_type","client_credentials")
                .build();
        System.out.println(formBody.contentType().type());
    }
}
