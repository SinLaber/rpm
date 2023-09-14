package com.fish.rpm.dao.util;

import okhttp3.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {
    /**
     * redis cache time
     */
    public static final long REDIS_CACHE_TIME = 100;

    public static final String LOG_ID = "logId";

    public static final String UC_ID = "ucId";

    public static final String OCCUR = "occur";

    public static final String APP_KEY = "appKey";

    public static final String DOMAIN_NAME = "https://cosinlab.com/";

    public static final String DOMAIN_LOGIN = "https://login.cosinlab.com/";

    public static final String DOMAIN_LOGOUT = "https://login.cosinlab.com/";

    public static final long DISABLED = -1001;

    public static final String UUID_VERIFY_SIGN = "UUID_VERIFY_SIGN";

    public static final List<String> APPKEY_LIST = Collections.singletonList("admin");

    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    public static final String HTTPS_URL = "https://my-json-server.typicode.com/typicode/demo/";
}
