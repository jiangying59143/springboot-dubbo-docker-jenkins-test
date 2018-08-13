package com.jy.utils.lottery;

import com.jy.utils.crawl.Crawl;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.jy.utils.crawl.Crawl.TAG_SELECTOR;

public class lottery {
    public static final Map<String,List<Integer>> lotteryMap = new LinkedHashMap<String, List<Integer>>();

    public static void getDataOfSinglePage(Document doc) {
        Element tbodyElement = Crawl.getElementFromDoc(doc, TAG_SELECTOR, "tbody").get(0);
        List<Element> trElements = Crawl.getElementFromElement(tbodyElement, TAG_SELECTOR, "tr");
        for (int i=2; i<trElements.size(); i++) {
            Element trElement = trElements.get(i);
            List<Element> tdElements = Crawl.getElementFromElement(trElement, TAG_SELECTOR, "td");
            if(tdElements != null && tdElements.size() >= 3){
                Element tdElement1 = tdElements.get(0);
                Element tdElement3 = tdElements.get(2);
                List<Element> emElements = Crawl.getElementFromElement(trElement, TAG_SELECTOR, "em");
                if(emElements != null && emElements.size() > 0) {
                    List<Integer> balls = new ArrayList<>();
                    for (Element emElement : emElements) {
                        String ballStr = emElement.text().startsWith("0") ? emElement.text().substring(1) : emElement.text();
                        int ball = Integer.parseInt(ballStr);
                        if (emElement.hasClass("rr")) {
                            balls.add(ball);
                        } else {
                            balls.add(ball);
                        }
                    }
                    lotteryMap.put(tdElement1.text(), balls);
                }
            }
        }
    }

    public static void main(String[] args) {
        Document doc = Crawl.getDocumentFromUrl("http://kaijiang.zhcw.com/zhcw/html/ssq/list.html");

        //获取总页数
        Element tbodyElement = Crawl.getElementFromDoc(doc, TAG_SELECTOR, "tbody").get(0);
        Elements elements = tbodyElement.children();
        int totalPage = Integer.parseInt(elements.get(elements.size()-1).child(0).child(1).child(0).text());

        //获取第一页和其他页数据
        getDataOfSinglePage(doc);
        for (int i = 2; i <= totalPage; i++) {
            doc = Crawl.getDocumentFromUrl("http://kaijiang.zhcw.com/zhcw/html/ssq/list_" + i + ".html");
            getDataOfSinglePage(doc);
        }

        lotteryMap.keySet().forEach((String key) -> {
            System.out.println(key + "   " + lotteryMap.get(key));
        });
    }
}
