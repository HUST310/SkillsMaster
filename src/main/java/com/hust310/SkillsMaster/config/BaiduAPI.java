package com.hust310.SkillsMaster.config;

/*百度的内容审核接口相关配置信息*/

import com.baidu.aip.contentcensor.AipContentCensor;

public class BaiduAPI {

    private static final String APP_ID = "34889997";
    private static final String API_KEY = "2od67QEHyfepWsXQvR0idplp";
    private static final String SECRET_KEY = "KKdGfAqowXsyWgNdrGpnKNI8X2NfaFyj";

    public static final AipContentCensor client=new AipContentCensor(BaiduAPI.APP_ID, BaiduAPI.API_KEY, BaiduAPI.SECRET_KEY);
}
