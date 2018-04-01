package io.github.devalves.osms.model.response.error;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class ServiceException
{
    private String messageId;
    private String text;
    private List<String> variables = new ArrayList<>();

    public ServiceException()
    {}

    public String getMessageId() {
        return messageId;
    }

    public String getText() {
        return text;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public void addVariable(String variable) {
        if(variable != null)
            variables.add(variable);
    }
}
