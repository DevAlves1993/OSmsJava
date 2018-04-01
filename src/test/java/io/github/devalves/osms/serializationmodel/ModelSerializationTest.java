package io.github.devalves.osms.serializationmodel;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import io.github.devalves.osms.core.CountryCode;
import io.github.devalves.osms.model.error.RequestError;
import io.github.devalves.osms.model.error.ResponseError;
import io.github.devalves.osms.model.error.ServiceException;
import io.github.devalves.osms.model.sms.OrangeSMS;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by Christian Amani on 01/04/2018.
 */
public class ModelSerializationTest {

    private Moshi moshi;
    private OrangeSMS orangeSMS;
    private RequestError requestError;
    private ResponseError responseError;

    @Before
    public void setup() {
        moshi = new Moshi.Builder()
                .build();
        orangeSMS = new OrangeSMS("string", "string", "string"
                , "string", CountryCode.ivoryCoast);
        requestError = new RequestError();
        ServiceException serviceException = new ServiceException();
        serviceException.setMessageId("MessageId");
        serviceException.setText("Error text");
        serviceException.setVariables(Collections.emptyList());
        requestError.setException(serviceException);
        responseError = new ResponseError();
        ResponseError.Error error = new ResponseError.Error("code error"
                , "message error", "description erro");
        responseError.setError(error);
    }

    @Test
    public void serializeOrangeSMS() {
        JsonAdapter<OrangeSMS> adapter = moshi.adapter(OrangeSMS.class);
        String json = adapter.toJson(orangeSMS);
        Assert.assertNotNull(json);
        Assert.assertEquals("{\"outboundSMSMessageRequest\":{\"address\":\"tel:+225string\"," +
                "\"outboundSMSTextMessage\":{\"message\":\"string\"},\"senderAddress\":\"tel:+225string\"," +
                "\"senderName\":\"string\"}}", json);
        System.out.println("json = " + json);
    }

    @Test
    public void serializeRequestError() {
        JsonAdapter<RequestError> adapter = moshi.adapter(RequestError.class);
        String json = adapter.toJson(requestError);
        Assert.assertNotNull(json);
        Assert.assertEquals("{\"exception\":{\"messageId\":\"MessageId\"" +
                        ",\"text\":\"Error text\",\"variables\":[]}}"
                , json);
        System.out.println("json = " + json);
    }

    @Test
    public void serializeResponseError() {
        JsonAdapter<ResponseError> adapter = moshi.adapter(ResponseError.class);
        String json = adapter.toJson(responseError);
        Assert.assertNotNull(json);
        Assert.assertEquals("{\"error\":{\"code\":\"code error\",\"description\":\"description erro\",\"message\":\"message error\"}}"
                , json);
        System.out.println("json = " + json);
    }
}
