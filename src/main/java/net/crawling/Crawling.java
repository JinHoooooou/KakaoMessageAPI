package net.crawling;

import java.io.IOException;
import java.util.List;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

public abstract class Crawling<T> {

  public abstract JSONObject crawlSchedule(String url) throws IOException;

  public abstract List<T> getTodayMatch(Document htmlDocument);

  public abstract String makeString(List<T> schedule);

  public static JSONObject getSchedule(String url) throws IOException {
    if (url.contains("kbo")) {
      return new Baseball().crawlSchedule(url);
    } else if (url.contains("epl")) {
      return new Soccer().crawlSchedule(url);
    }
    return new JSONObject().put("schedules", "준비중");
  }
}
