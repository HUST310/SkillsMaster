package com.hust310.SkillsMaster;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.util.Base64Util;
import com.hust310.SkillsMaster.config.BaiduAPI;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


public class jsouptest {
    @Test
    public void toHtml() throws IOException {
        Document document = Jsoup.parse(new File("D:\\devel\\SkillsMaster\\src\\main\\resources\\static\\blogs\\32b29f76-3246-4949-b59d-aadeb9169b49.html"));
        Elements img = document.getElementsByTag("img");

        String text = img.attr("src").substring(23);
        JSONObject src = BaiduAPI.client.imageCensorUserDefined(Base64Util.decode(text), null);
        System.out.println(src.toString());
    }
}
