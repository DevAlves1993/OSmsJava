# OSmsJava
OSmsJava is a library allowing you to consume easily [API REST SmsApi of orange Ivory Coast (zone AMEA)](https://www.orangepartner.com/SMS-CI-API) .
Do not use this library in production. It is under development. It will be available very soon.

## Usage


### Download 

Download via Maven

    <dependency>
        <groupId>org.akanza</groupId>
        <artifactId>osms-java</artifactId>
        <version>1.0.0</version>
    </dependency>

or Gradle

    compile 'org.akanza:osms-java:1.0.0

#### OSms


#### How Send a SMS : 

The SMS object it is the representation object of your SMS have send.
It is composed of three fields (`String address`, `String senderAddress`,`String content`).

* the field address it is your number phone.
* the field senderAddress it is the number phone recipient
* the field content it is content of message

The ResponseSMS object it is the representation of the Json response returned by  orange smsAPI after having sent a message.
For send a sms Call method `sendSMS(Token token, SMS sms, Callback callback)` of object ServiceSMS.

* The parameter token it is your Token
* The parameter sms it is your sms
* The parameter callback contains the action in executed after the reception of http response

For example:

```java
    public static void main(String... args)
    {
          
    }
```

##### ResponseSMS

The ResponseSMS object it is the representation of the Json response returned by  orange smsAPI after having sent a message.
It contains the different information.
The ResponseSMS contains two Object :

* SMSResponse smsResponse

###### SMSResponse

SMSResponse contains some information. 
SMSResponse contains the four Objects : 

* String address : End user ID
* String senderAddress : The MSISDN of the sender
* String senderName : URL-escaped name of the sender to appear on the terminal is the address to whom a responding SMS may be sent.
* SMSContent smsContent : SMSContent contains the Object String which of sms content
    * String message : The content message
    

#### How consulted numbers sms remainder :

The ContractsSMS object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of remaining SMS.
For obtain remaining sms, call method `obtainsContractsSMS(Token token, Callback callback)` of object ServiceSMS.

* The parameter token it is your Token
* The parameter callback contains the action in executed after the reception of http response

At first you have to be interested in the object PartnerContracts. I invite you to glance on the source code.

For example :

```java
    public static void main(String... args)
    {
            
    }
```
##### PartnerContracts

The PartnerContracts Object contains some information. 
The PartnerContracts Object contains two Object : 

* String partnerId : Partner Identifier provided by Orange Partner
* Contract[] contracts : List of contracts associated to the partner

###### Contract

The Contract Object contain some information of contract associated to the partner.
The Contract Object contains three Object : 

* String service : Technical name of the subscribed service 
* String contractDescription : Overall service contract description
* ServiceContracts serviceContracts : List of country-specific contracts

###### ServiceContracts

The ServiceContracts Object contain some information.
The ServiceContracts Object contain several Object : 

* String country : ISO 3166-1 alpha 3 country code
* String service : Technical name of the subscribed service
* String contractId : Contract identifier
* int availableUnits : Available units in the contract
* String expires : Contract expiration date, ISO 8601 format
* String scDescription : Country-specific contract description, contains the number of units to consume and the expiration date



#### How consulted the statistics of use of the application 

The StatisticSMS object it is the representation object of Json response returned by orange smsAPI having sent a request of consultation of statistics usage.
For obtain statistics sms, call method `obtainStatisticSMS(Token token, Callback callback)` of object ServiceSMS.

* The parameter token it is your Token
* The parameter callback contains the action in executed after the reception of http response

At first you have to be interested in the object PartnerStatistics. I invite you to glance on the source code.

For example :
 
```java
    public static void main(String... args)
    {
        
    }
```

##### PartnerStatistics

The PartnerStatistics Object contains some information. 
The PartnerStatistics Object contains two Object : 

* String partnerId : Partner Identifier provided by Orange Partner
* Statistic[] Statistics : List of Statistic associated to the partner

###### Statistic

The Statistic Object contains some information of contract associated to the partner.
The Statistic Object contains two Object :

* String service : Technical name of the subscribed service
* ServiceStatistic[] serviceStatistics : List of statistics associated to the service

###### ServiceStatistic

The ServiceStatistic Object contains some information statistics associated to the service
The ServiceStatistic Object contains two Object : 

* String country : ISO 3166-1 alpha 3 country code 
* CountyStatistic[] countryStatistics : List of statistics associated to the country

###### CountyStatistic

The CountyStatistic Object contains some information statistics associated to the country
The CountyStatistic Object contains two Object : 

* String applicationId : Application identifier of the partner provided by Orange Partner
* int usage : Number of units consumed by the application 


#### How consulted the historic purchase :

The HistoricPurchase object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of purchase historic.
For obtain historic purchase, call method `obtainHistoricSMS(Token token, Callback callback)` of object ServiceSMS.

* The parameter token it is your Token
* The parameter callback contains the action in executed after the reception of http response

At first you have to be interested in the object PurchaseOrders. I invite you to glance on the source code.

For example :

```java
    public static void main(String... args)
    {
        
    }
```

##### PurchaseOrder

The PartnerStatistics Object contains some information. 
The PartnerStatistics Object contains several Objects : 

* String purchaseOrderId : Purchase Order Identifier
* String mode : Payment mode
* String bundleId : Identifier of the purchased bundle
* String bundleDescription : Full description of the purchased bundle
* String partnerId : Partner identifier provided by Orange Partner
* Input[] inputs : JSON structure containing all the necessary inputs to perform the purchase (depends on the method used)
* OrderExecutionInformation orderExecutionInformation : detailed information about the purchase

###### Input

The Input Object contains some information.
The Input Object contains two Objects :

* String type : input type
* String vale : input value

###### OrderExecutionInformation

The OrderExecutionInformation Object contains some information.
The OrderExecutionInformation Object several Objects : 

* String date : Purchase date an ISO 3166 format
* int amount :  Charged amount
* String currency : Currency used
* String service : Technical name of the subscribed service
* String country : ISO 3166-1 alpha 3 country code
* String contractId : Associated service contract identifier



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