package utils;

import android.Manifest;

/**
 * Title: Permissions
 * Description:
 * Copyright: Copyright (c) 2014-2016 gjfax.com
 * Company: 广金所
 * Author: 刘加彦
 * Date: 2016/7/18
 * Version: 1.0
 */
public  enum Permissions{

    readStorage(Manifest.permission.READ_EXTERNAL_STORAGE),
    writeStorage(Manifest.permission.WRITE_EXTERNAL_STORAGE),
    internet(Manifest.permission.INTERNET),
    access_network_state(Manifest.permission.ACCESS_NETWORK_STATE),
    access_wifi_state(Manifest.permission.ACCESS_WIFI_STATE),
    record_audio(Manifest.permission.RECORD_AUDIO),
    write_contacts(Manifest.permission.WRITE_CONTACTS),
    send_sms(Manifest.permission.SEND_SMS),
    call_phone(Manifest.permission.CALL_PHONE);

    public String getValue() {
        return p.toString();
    }

    public void setValue(String p) {
        this.p = p;
    }

    private String p;

    Permissions(String permission){
        this.p = permission;
    }
}
