package io.github.devalves.osms;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;

import io.github.devalves.osms.core.HttpApiOrange;
import io.github.devalves.osms.core.exception.HttpApiOAuthOrangeException;
import io.github.devalves.osms.core.exception.HttpApiOrangeException;
import io.github.devalves.osms.model.sms.OrangeSMS;
import io.github.devalves.osms.model.Token;
import io.github.devalves.osms.model.historic.HistoricPurchase;
import io.github.devalves.osms.model.sms.ResponseSMS;
import io.github.devalves.osms.model.error.ResponseError;
import io.github.devalves.osms.model.error.ServiceError;
import io.github.devalves.osms.model.error.ServiceException;
import io.github.devalves.osms.core.Builder;
import io.github.devalves.osms.model.partnercontract.ContractsSMS;
import io.github.devalves.osms.model.subscription.ResponseSubscription;
import io.github.devalves.osms.model.statistic.StatisticSMS;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSource;



public class OSms implements HttpApiOrange
{
    private OkHttpClient client;
    private Token token;
    private Moshi moshi;

    private OSms(Token token, OkHttpClient client) {
        this.token = token;
        this.client = client;
        moshi = new Moshi.Builder()
                .build();
    }

    public Token getToken() {
        return this.token;
    }

    @Override
    public ResponseSMS sendSms(OrangeSMS sms) throws IOException, HttpApiOrangeException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("smsmessaging")
                .addPathSegment(VERSION_API)
                .addPathSegment("outbound")
                .addEncodedPathSegment(sms.getMessage().getSenderAddress())
                .addPathSegment("requests")
                .build();
        JsonAdapter<OrangeSMS> adapter = moshi.adapter(OrangeSMS.class);
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.createAccess())
                .addHeader("Content-Type","application/json")
                .post(RequestBody.create(JSON_MEDIA,adapter.toJson(sms)))
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            return jsonToResponseSMS(response);
        } else {
            ServiceError error = jsonToServiceError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public ResponseSubscription subscriptionApi(String senderAddress
            , ResponseSubscription subscription) throws IOException, HttpApiOrangeException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("smsmessaging")
                .addPathSegment(VERSION_API)
                .addPathSegment("outbound")
                .addPathSegment(senderAddress)
                .addPathSegment("subscriptions")
                .build();
        JsonAdapter<ResponseSubscription> adapter = this.moshi.adapter(ResponseSubscription.class);
        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader(HEADER_AUTHORISATION,token.createAccess())
                .post(RequestBody.create(JSON_MEDIA,adapter.toJson(subscription)))
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            return jsonToResponseSubscription(response);
        } else {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public ResponseSubscription checkSubscriptionApi(String subId) throws HttpApiOrangeException {
        // TODO : implement later
        return null;
    }

    @Override
    public void unSubscriptionApi(String senderAddress, String subId) throws HttpApiOrangeException {
        // TODO : implement later
    }

    @Override
    public StatisticSMS obtainStatisticSMS() throws IOException, HttpApiOrangeException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("statistics")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.createAccess())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            return jsonToStatisticSMS(response);
        } else {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public ContractsSMS obtainsContractsSMS() throws IOException, HttpApiOrangeException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("contracts")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.createAccess())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            return jsonToContractsSMS(response);
        } else {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public HistoricPurchase obtainHistoricSMS() throws IOException, HttpApiOrangeException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("purchaseorders")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.createAccess())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            return jsonToHistoricPurchase(response);
        } else {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    private HistoricPurchase jsonToHistoricPurchase(Response response) throws IOException {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<HistoricPurchase> historicPurchaseJsonAdapter = moshi.adapter(HistoricPurchase.class);
        return historicPurchaseJsonAdapter.fromJson(source);
    }

    private ResponseSMS jsonToResponseSMS(Response response) throws IOException {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ResponseSMS> responseSMSJsonAdapter = moshi.adapter(ResponseSMS.class);
        return responseSMSJsonAdapter.fromJson(source);
    }

    private ResponseSubscription jsonToResponseSubscription(Response response) throws IOException {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ResponseSubscription> responseSubscriptionJsonAdapter = moshi.adapter(ResponseSubscription.class);
        return responseSubscriptionJsonAdapter.fromJson(source);
    }

    private StatisticSMS jsonToStatisticSMS(Response response) throws IOException {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<StatisticSMS> statisticSMSJsonAdapter = moshi.adapter(StatisticSMS.class);
        return statisticSMSJsonAdapter.fromJson(source);
    }

    private ContractsSMS jsonToContractsSMS(Response response) throws IOException {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ContractsSMS> contractsSMSJsonAdapter = moshi.adapter(ContractsSMS.class);
        return contractsSMSJsonAdapter.fromJson(source);
    }

    private ResponseError jsonToResponseError(Response response) throws IOException {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ResponseError> responseErrorJsonAdapter = moshi.adapter(ResponseError.class);
        return responseErrorJsonAdapter.fromJson(source);
    }

    private ServiceError jsonToServiceError(Response response) {
        ServiceError serviceError = null;
        BufferedSource source = response.body()
                .source();
        JsonReader reader = JsonReader.of(source);
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("requestError")) {
                    ServiceException serviceException = getServiceException(reader);
                    serviceError = initServiceError(serviceError, serviceException);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serviceError;
    }

    private ServiceError initServiceError(ServiceError serviceError, ServiceException serviceException) {
        ServiceError.RequestError requestError =  new ServiceError.RequestError();
        requestError.setException(serviceException);
        serviceError = new ServiceError();
        serviceError.setRequestError(requestError);
        return serviceError;
    }

    private ServiceException getServiceException(JsonReader reader) {
        ServiceException serviceException = new ServiceException();
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String exception = reader.nextName();
                if (exception.equals("serviceException") || exception.equals("policyException")) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String name = reader.nextName();
                        if (name.equals("messageId")) {
                            String messageId = reader.nextString();
                            serviceException.setMessageId(messageId);
                        } else if (name.equals("text")) {
                            String text = reader.nextString();
                            serviceException.setText(text);
                        } else if (name.equals("variables")) {
                            readVariableArray(reader, serviceException);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serviceException;
    }

    private void readVariableArray(JsonReader reader, ServiceException serviceException) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            String variable = reader.nextString();
            serviceException.addVariable(variable);
        }
    }

    public static class BuilderOSms extends Builder {
        @Override
        public OSms build() throws IOException, HttpApiOAuthOrangeException {
            if (id != null && secretCode != null) {
                Token token = obtainsToken();
                return new OSms(token,Builder.client);
            }
            return null;
        }

        @Override
        public BuilderOSms id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public BuilderOSms secretCode(String secretCode) {
            this.secretCode = secretCode;
            return this;
        }

        @Override
        public BuilderOSms okHttpClient(OkHttpClient client) {
            Builder.client = null;
            Builder.client = client;
            return this;
        }
    }
}
