package io.github.devalves.osms.model.sms;

import com.squareup.moshi.Json;
import io.github.devalves.osms.core.CountryCode;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class OrangeSMS
{
    @Json(name = "outboundSMSMessageRequest")
    private SMSMessage message;


    /**
     * @param address The Sender address
     * @param senderAddress The current address
     * @param content The content of OrangeSMS
     * @param code Country code
     */
    public OrangeSMS(String address, String senderAddress, String content, CountryCode code) {
        message = new SMSMessage(address,senderAddress,content,code);
    }

    public OrangeSMS(String address, String senderAddress, String senderName, String content, CountryCode code) {
        message = new SMSMessage(address,senderAddress,senderName,content,code);
    }

    public SMSMessage getMessage() {
        return message;
    }

    public void setMessage(SMSMessage message) {
        this.message = message;
    }

}
