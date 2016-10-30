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
For generate a Token object, used one of the static methods of Class abstract **`FactoryToken`**.

* **`FactoryToken.getToken(String id,String secretCode)`** : return a simple Token object
* **`FactoryToken.setToken(String id,String secretCode,Callback callback)`** : Instantiate an Token object
* **`FactoryToken.getFutureToken(String id,String secretCode)`** : return a Token Future.

Example with **`FactoryToken.getToken()`** :

    
    String secretCode = "secretCode";
    String id = "id";
    public static void main(String... args)
    {
        try
        {
            Token token  = FactoryToken.getToken();
        }
        catch (ServiceException e)
        {
            ResponseError responseError = e.getResponseError();
            ...................................................
            ...................................................
            e.printStackTrace();
        }
    }


Example with **`FactoryToken.setToken(String id,String secretCode,Callback callback)`** :

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
        FactoryToken.setToken(id,secretCode,callback);
        if(token != null)
        {
            String accessToken = token.getAccessToken();
            String tokenType = token.getTokenType();
            ..........................................
        }
    }


Example with **`FactoryToken.getFutureToken(String id,String secretCode)`** :

    String secretCode = "secretCode";
    String id = "id";
    public static void main(String... args)
    {
        try
        {
            Future<Token>  futureToken =  FactoryToken.getFutureToken(id,secretCode);
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


### The Callback



#### How Send a SMS : 

The SMS object it is the representation object of your SMS have send.
It is composed of three fields (`String address`, `String senderAddress`,`String content`).

* the field address it is your number phone.
* the field senderAddress it is the number phone recipient
* the field content it is content of message

The ResponseSMS object it is the representation of the JSon response returned by  orange smsAPI after having sent a message.
For send a sms Call method `sendSMS(Token token, SMS sms, Callback callback)` of object ServiceSMS.

* The parameter token it is your Token
* The parameter sms it is your sms
* The parameter callback (contient les actions à executés après la reception de la réponse)

For example:

    public static void main(String... args)
    {
        // Action which perform (en cas de) success
        OnSuccess onSuccess = (b,r,i) -> {
            ResponseSMS responseSMS = (ResponseSMS) b;
            String outBoundSMSMessageRequest = responseSMS.getOutBoundSMSMessageRequest();
            String resourceURL = responseSMS.getResourceURL();
            System.out.println("My out bound SMS message request is : "+outBoundSMSMessageRequest);
            System.out.println("My url resource is : "+resourceURL);
            ResponseHeader responseHeader = (ResponseHeader) r;
            ....................................................
            ....................................................
            System.out.println("The status code is : "+i);
        }
        
        // Action which perform (en cas d'echec)
        OnFailure onFailure = (r,m,i,) -> {
            String message = r.getMessage();
            String description = r.getDescription();
            int code = r.getCode();
            System.out.println("Error message is : "+message);
            System.out.println("Error description is : "+description);
            System.out.println("The status code is : "+code):
        }
        
        // Action which perform (en cas d'exception)
        OnThrowable onThrowable = (t) -> {
            String message = t.getMessage();
            ................................
            ................................
            ................................
        }
        
        Callback callback = new Callback(onSuccess,onFailure,onThrowable);
        
        Token token = FactoryToken.getToken();
        ServiceSMS serviceSMS = new Service();
        
        SMS sms = new SMS("Your number phone","Number phone of recipient","Hello world");
        
        serviceSMS.sendSMS(token,sms,callback);

    }


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