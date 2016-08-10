package org.akanza;

/**
 * Created by AMANI on 10/08/2016.
 */

@FunctionalInterface
public interface callback
{

    void onSuccess();

    default void onFailure(int statusCode)
    {
        return;
    }

    default void onThrowable(Throwable throwable)
    {
        throwable.printStackTrace();
    }
}
