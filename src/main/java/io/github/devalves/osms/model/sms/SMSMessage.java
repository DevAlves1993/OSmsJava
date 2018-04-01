package io.github.devalves.osms.model.sms;

import com.squareup.moshi.Json;
import io.github.devalves.osms.core.CountryCode;

/**
 * <p>Class which contains a elements a base of OrangeSMS</p>
 *
 * @author Amani Christian Cyrille Alves
 */
public class SMSMessage {
    private String address;
    @Json(name = "outboundSMSTextMessage")
    private SMSContent content;
    private String senderAddress;
    private String senderName; // TODO verify serialisation

    public SMSMessage(String address, String senderAddress, String content, CountryCode countryCode) {
        String code = countryCode.getCode();
        this.address ="tel:"+code+address;
        this.senderAddress ="tel:"+code+senderAddress;
        this.content = new SMSContent(content);
        this.senderName = null;
    }

    public SMSMessage(String address, String senderAddress, String senderName, String content
            , CountryCode countryCode) {
        String code = countryCode.getCode();
        this.address = "tel:" + code + address;
        this.senderAddress ="tel:"+code+senderAddress;
        this.content = new SMSContent(content);
        this.senderName = senderName;
    }

    public String getContent() {
        return content.getMessage();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setContent(String content) {
        this.content.setMessage(content);
    }

    public String getAddress() {
        return address;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}