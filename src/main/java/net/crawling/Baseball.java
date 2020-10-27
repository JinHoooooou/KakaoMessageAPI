package net.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Baseball extends Crawling<Baseball> {

  private String time;
  private String awayTeam;
  private String score;
  private String homeTeam;
  private String place;

  @Override
  public JSONObject crawlSchedule(String url) throws IOException {
    Document htmlDocument = Jsoup.connect(url).get();

    List<Baseball> schedules = getTodayMatch(htmlDocument);
    JSONObject result = new JSONObject();
    result.put("schedules", makeString(schedules));
    return result;
  }

  public List<Baseball> getTodayMatch(Document htmlDocument) {
    List<Baseball> schedules = new ArrayList<>();
    Element matchTable = htmlDocument.selectFirst("tbody._scroll_content");
    if (matchTable != null) {
      for (Element element : matchTable.getElementsByTag("tr")) {
        String time = element.select("span.bg_none").text();
        String place = element.select("td.place").text();
        String awayTeam = element.selectFirst("td.l_team").text().split(" ")[0];
        String score = element.select("td.score").text();
        String homeTeam = element.selectFirst("td.r_team").text().split(" ")[0];
        schedules.add(new Baseball(time, awayTeam, score, homeTeam, place));
      }
    }
    return schedules;
  }

  @Override
  public String makeString(List<Baseball> schedule) {
    StringBuilder result = new StringBuilder("Kbo 일정/결과\n");
    if (schedule.isEmpty()) {
      result.append("경기 일정이 없습니다.\n");
    } else {
      String lastTime = "";
      for (Baseball match : schedule) {
        if (!lastTime.equals(match.getTime())) {
          result.append(match.getTime()).append("\n");
          lastTime = match.getTime();
        }
        result.append(String.format("%s(H) %s %s(A)\n",
            match.getHomeTeam(), match.getScore(), match.getAwayTeam()));
      }
    }
    result.append("\n\n");
    return result.toString();
  }
}
