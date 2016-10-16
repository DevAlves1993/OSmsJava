# OSmsJava
OSmsJava is a library allowing you to consume easily [API REST SmsApi of orange Ivory Coast (zone AMEA)](https://www.orangepartner.com/SMS-CI-API) .
Do not use this library in production. It is under development. It will be available very soon.

## Usage


### Download 

Download via Maven

    <dependency>
        <groupId>org.Akanza</groupId>
        <artifactId>OSmsJava</artifactId>
        <version>0.1.9</version>
    </dependency>

or Gradle

    compile 'org.Akanza:OSmsJava:0.1.9

#### How generate a token

The Token object it is the representation object of the Json response returned by the orange smsAPI.
For generate a Token object, used one of the static methods of Class abstract **`FactoryService`**.

* **`FactoryService.getToken(String id,String secretCode)`** : return a simple Token object
* **`FactoryService.setToken(String id,String secretCode,Callback callback)`** : Instantiate an Token object
* **`FactoryService.getFutureToken(String id,String secretCode)`** : return a Token Future.

Example with **`FactoryService.getToken()`** :

    
    String secretCode = "secretCode";
    String id = "id";
    public static void main(String... args)
    {
        try
        {
            Token token  = FactoryService.getToken();
        }
        catch (ServiceException e)
        {
            ResponseError responseError = e.getResponseError();
            ...................................................
            ...................................................
            e.printStackTrace();
        }
    }


Example with **`FactoryService.setToken(String id,String secretCode,Callback callback)`** :

    String secretCode = "secretCode";
    String id = "id";
    Token token = null;
    public static void main(String... args)
    {
        Callback callback = new Callback((baseResponse, responseHeader, statusCode) ->
            {
                token = (Token) baseResponse;
                ............
                ............
            });
        Token token = null;
        FactoryService.setToken(id,secretCode,callback);
        if(token != null)
        {
            String accessToken = token.getAccessToken();
            String tokenType = token.getTokenType();
            ..........................................
        }
    }


Example with **`FactoryService.getFutureToken(String id,String secretCode)`** :

    String secretCode = "secretCode";
    String id = "id";
    public static void main(String... args)
    {
        try
        {
            Future<Token>  futureToken =  FactoryService.getFutureToken(id,secretCode);
            ..........................................................................
            ..........................................................................
            ..........................................................................
            Token token = futureToken.get();
            if(token != null)
            {
                String accessToken = token.getAccessToken();
                String tokenType = token.getTokenType();
                ........................................
            }
            ..........................................................................
            ..........................................................................
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ServiceException es)
        {
            ResponseError responseError = e.getResponseError();
            ...................................................
            ...................................................
            e.printStackTrace();
        }
    }

#### How Send a SMS (DEPRECATED! Don't Use) : 

The SMS object it is the representation object of your SMS have send.
It is composed of three fields (`String address`, `String senderAddress`,`String content`).

* the field address it is your number phone.
* the field senderAddress it is the number phone recipient
* the field content it is content of message
The ResponseSMS object it is the representation of the JSon response returned by  orange smsAPI after having sent a message.
For send a sms Call method `sendSMS(SMS sms ,,SMSHeader smsHead)` of object GenerateService.
* The SMSHeader parameter is used to store the data heading returned by the apiSMS.

For example:

    public static void main(String... args)
    {
        try
        {
            SMSHeader smsHeader;
            ResponseSMS responseSMS

            // the address and the senderAddress must be written on this form.
            // the Iso code of the country concatenated to the number
            // +XXXxxxxxxxx
            String address = "+22500000000";
            String senderAddress = "+22511111111";
            String content = "my content";
            SMS sms = new SMS(address,senderAddress,content);
            GenerateService service = new GenerateService("5454656","mon code secret");
            Token token = service.generatedToken();
            responseSMS = service.sendSMS(token,sms,smsHeader);

            String contentMessage = responseSMS.getOutBoundSMSMessageRequest().getOutboundSMSTextMessage();
            String senderAddress = responseSMS.getOutBoundSMSMessageRequest().getSenderAddress();
            String ResourceUrl = responseSMS.getResourceUrl();

            // recuperation of information of  headers
            String contentType = smsHeader.contentType;
            String contentLength = smsHeader.contentLength;
            String location = smsHeader.location;
            String date = smsHeader.date;
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

Mark:In order to send SMS since our API, you must first of all buy a bundle SMS with Orange.In order to
to facilitate the integration of API, you have the possibility to buy a bundle "starter".

#### How consulted numbers sms remainder (DEPRECATED! Don't Use) :

The RemainderSMS object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of remaining SMS.
At first you have to be interested in the object PartnerContracts. I invite you to glance on the source code.

For example :

    public static void main(String... args)
    {
        try
        {
            RemainderSMS remainderSMS;
            GenerateService service = new GenerateService("5454656","secret code");
            Token token = service.generatedToken();
            remainderSMS = service.remainderSMS(token);
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

#### How consulted the statistics of use of the application (DEPRECATED! Don't Use) :

The StatisticSMS object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of statistics ussage.
At first you have to be interested in the object PartnerStatistics. I invite you to glance on the source code.

For example :

    public static void main(String... args)
    {
        try
        {
            StatisticSMS statistics;
            GenerateService service = new GenerateService("5454656","my secret code");
            Token token = service.generatedToken();
            statistics = service.statisticSMS(token);
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

#### How consulted the historic purchase (DEPRECATED! Don't Use) :

The HistoricPurchase object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of purchase historic.
At first you have to be interested in the object PurchaseOrders. I invite you to glance on the source code.

For example :

    public static void main(String... args)
    {
        try
        {
            HistoricPurchase historic;
            GenerateService service = new GenerateService("5454656","secret code");
            Token token = service.generatedToken();
            historic = service.historicPurchase(token);
            PurchaseOrders[] purchaseOrders = historic.getPurchaseOrders();
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
the method `getPurchaseOrders()` return a table of PurchaseOrders.

## Authors and Contributors
In 2015, Amani Christian Cyrille Alves (@DevAlves1993) founded OSmsJava.

## Contacts

* Gmail : [alvesamani@gmail.com] (mailto:alvesamani@gmail.com)
* Twitter [@cyrilleamani] (https://twitter.com/cyrilleamani)

## License

[MIT License](http://www.opensource.org/licenses/mit-license.php) 
[Introduction to the MIT License](https://opensource.org/osd-annotated)

## Contributing
If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.
Please also make sure your code compiles by running `mvn clean verify`

The list of the contributions which would be welcome.

* Documentation : Participated in the writing of the documentation.
* Example : Creating several examples showing the use of the library.
* Bugs : Report bugs with of issues.