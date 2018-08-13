package com.jy.utils.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Crawl {
    public static final int TAG_SELECTOR = 0;
    public static final int CLASS_SELECTOR = 1;
    public static final int ID_SELECTOR = 2;

    /**
     *
     * @param url
     * @return
     */
    public static Document getDocumentFromUrl(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     *
     * @param doc
     * @param seletor TAG ID CLASS
     * @param searchStr
     * @return
     */
    public static List<Element> getElementFromDoc(Document doc, int seletor, String searchStr){
        Element bodyElement = doc.getElementsByTag("body").get(0);
        List<Element> list = new ArrayList<>();
        if(TAG_SELECTOR == seletor) {
            list = bodyElement.getElementsByTag(searchStr);
        }else if(ID_SELECTOR == seletor) {
            list.add(bodyElement.getElementById(searchStr));
        }else if(CLASS_SELECTOR == seletor){
            list = bodyElement.getElementsByClass(searchStr);
        }
        return list;
    }

    /**
     *
     * @param parentElement
     * @param seletor
     * @param searchStr
     * @return
     */
    public static List<Element> getElementFromElement(Element parentElement, int seletor, String searchStr){
        List<Element> list = new ArrayList<>();
        if(TAG_SELECTOR == seletor) {
            list = parentElement.getElementsByTag(searchStr);
        }else if(ID_SELECTOR == seletor) {
            list.add(parentElement.getElementById(searchStr));
        }else if(CLASS_SELECTOR == seletor){
            list = parentElement.getElementsByClass(searchStr);
        }
        return list;
    }
}
