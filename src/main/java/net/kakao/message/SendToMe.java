package net.kakao.message;

import static net.constants.Constants.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SendToMe {

  String url;
  RestTemplate restTemplate;
  HttpHeaders header;

  public SendToMe() {
    header = new HttpHeaders();
    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    restTemplate = new RestTemplate();
  }

  public String sendMessageToMe(String text) {
    String accessToken = getAccessTokenUsingRefreshToken().getString("access_token");
    url = KAKAO_SEND_TO_ME_URL;
    header.add("Authorization", "Bearer " + accessToken);

    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("template_object", buildTemplateObject(text));
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, header);

    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    return response.getStatusCode().name();
  }

  public JSONObject getAccessTokenUsingRefreshToken() {
    url = KAKAO_OAUTH_URL;

    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("grant_type", "refresh_token");
    requestBody.add("client_id", System.getenv("CLIENT_ID"));
    requestBody.add("refresh_token", System.getenv("REFRESH_TOKEN"));

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, header);
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

    return new JSONObject(response.getBody());
  }

  private String buildTemplateObject(String text) {
    JSONObject link = new JSONObject();
    link.put("web_url", NAVER_SPORTS_MAIN_URL);
    link.put("mobile_web_url", NAVER_SPORTS_MAIN_URL);

    JSONObject templateObject = new JSONObject();
    templateObject.put("object_type", "text");
    templateObject.put("text", text);
    templateObject.put("link", link);
    templateObject.put("buttons", getButtons());
    return templateObject.toString();
  }

  private JSONArray getButtons() {
    JSONArray buttons = new JSONArray();
    JSONObject button1 = new JSONObject();
    JSONObject link1 = new JSONObject();
    link1.put("web_url", NAVER_KBO_SCHEDULE_URL);
    link1.put("mobile_web_url", NAVER_KBO_SCHEDULE_URL);
    button1.put("title", "Kbo 일정");
    button1.put("link", link1);

    JSONObject button2 = new JSONObject();
    JSONObject link2 = new JSONObject();
    link2.put("web_url", NAVER_EPL_SCHEDULE_URL);
    link2.put("mobile_web_url", NAVER_EPL_SCHEDULE_URL);
    button2.put("title", "Epl 일정");
    button2.put("link", link2);

    buttons.put(button1);
    buttons.put(button2);
    return buttons;
  }
}
