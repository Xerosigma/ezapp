package com.nestorledon.ezmvp.forms;

/**
 * Created by nestorledon on 5/2/15.
 */
public interface HasObject {

    Object getObjectData();

    int getElementId();

    void setError(String error);

}
