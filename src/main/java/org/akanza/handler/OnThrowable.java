package org.akanza.handler;

/**
 * Created by Christian Amani on 25/08/2016.
 */
@FunctionalInterface
public interface OnThrowable
{

    /**
     * <p>This method runs when the call of the SMS API throws an exception.</p>
     * @param throwable
     */
    void onThrowable(Throwable throwable);
}
