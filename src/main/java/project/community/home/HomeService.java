package project.community.home;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    private static String News_URL = "https://sports.news.naver.com/index";
    private static int timeoutMillis = 60000; // 30초
    public List<NewsDto> getNews() {
        List<NewsDto> newsList = new ArrayList<>();
        Document document = null;

        try {
            Connection connection = Jsoup.connect(News_URL).timeout(timeoutMillis);
            document = connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements contents = document.select(".today_list li");

        for (Element content : contents) {
            NewsDto news = NewsDto.builder()
                    .title(content.select(".title").text())		// 제목
                    .url(content.select(".link_today").attr("abs:href"))		// 링크
                    .build();
            newsList.add(news);
        }
        return newsList;
    }
}
