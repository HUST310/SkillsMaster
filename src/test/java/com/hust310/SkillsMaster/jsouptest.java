package com.hust310.SkillsMaster;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;


public class jsouptest {
    //    @Test
//    public void toHtml() throws IOException {
//        Document document = Jsoup.parse(new File("D:\\devel\\SkillsMaster\\src\\main\\resources\\static\\blogs\\test.html"));
//        Elements img = document.getElementsByTag("img");
//        AipContentCensor client = new AipContentCensor(BaiduAPI.APP_ID, BaiduAPI.API_KEY, BaiduAPI.SECRET_KEY);
//
//        for (Element node : img) {
//            String text = node.attr("src").substring(23);
//            System.out.println(text);
//            JSONObject src = client.imageCensorUserDefined(Base64Util.decode(text), null);
//            System.out.println(src.toString());
//
//        }
//    }
    @Test
    public void testMap() {
        LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
        map.put("name", new String[]{"jm"});
        System.out.println(map.get("name").toString());
    }
}
