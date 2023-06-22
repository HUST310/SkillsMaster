package com.hust310.SkillsMaster;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.contentcensor.EImgType;
import com.hust310.SkillsMaster.config.BaiduAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

//@SpringBootTest
public class BaiduApiTest {

//    public static final String APP_ID = "34889997";
//    public static final String API_KEY = "2od67QEHyfepWsXQvR0idplp";
//    public static final String SECRET_KEY = "KKdGfAqowXsyWgNdrGpnKNI8X2NfaFyj";


    @Test
    public void testText() throws JSONException {

        //AipContentCensor client=new AipContentCensor(BaiduAPI.APP_ID, BaiduAPI.API_KEY, BaiduAPI.SECRET_KEY);
        String text="蔡英文";
        JSONObject response = BaiduAPI.client.textCensorUserDefined(text);
        System.out.println(response.get("conclusion"));
    }

    @Test
    public void testImage() {
        //AipContentCensor client=new AipContentCensor(BaiduAPI.APP_ID, BaiduAPI.API_KEY, BaiduAPI.SECRET_KEY);
        String url="https://tse4-mm.cn.bing.net/th/id/OIP-C.kHinIF0bOKfFmAOxpibyfwAAAA?w=132&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7";
        JSONObject response = BaiduAPI.client.imageCensorUserDefined(url, EImgType.URL, null);
        System.out.println(response.toString());
    }

//***************需要打开上面的注解**************************
//    @Value("${baidu-api.APP_ID}")
//     String APP_ID ;
//     @Value("${baidu-api.API_KEY}")
//     String API_KEY ;
//     @Value("${baidu-api.SECRET_KEY}")
//     String SECRET_KEY ;
//
//    @Test
//    public void testText() {
//        AipContentCensor client=new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
//        String text="蔡|英|文";
//        JSONObject response = client.textCensorUserDefined(text);
//        System.out.println(response.toString());
//    }

}
