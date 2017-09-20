package com.webSpider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * User: yli
 * Date: 2017/6/9
 * Time: 14:39
 */
public class CeairFlightSearchProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    private String[] cityCode={"sha","nay"};
    private String[] dates ={"170610","170611"};

    public void process(Page page) {
        List<String> urls = new ArrayList<String>();
        for (String depCity:cityCode){
            for (String arrCity:cityCode){
                if(depCity.equals(arrCity)){
                    continue;
                }
                for (String date : dates){
                    String url = "http://www.ceair.com/flight2014/"+depCity+"-"+arrCity+"-"+date+"_CNY.html";
                    urls.add(url);
                }
            }
        }
        page.addTargetRequests(urls);
        page.putField("name", page.getHtml().css("section#main-area").css("section.flight-booking").css("hgroup#flight-departure").css("figure#flight-info").css("article.section").css("hgroup.detail").css("ul.detail-info").css("li.clearfix").toString());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CeairFlightSearchProcessor()).addUrl("http://www.ceair.com/flight2014/").run();
    }
}
