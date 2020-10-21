package net.kakao.message;

import java.net.URISyntaxException;
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
    String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
    header.add("Authorization", "Bearer " + accessToken);

    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("template_object", buildTemplateObject(text));
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, header);
    
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    return response.getStatusCode().name();
  }

  public JSONObject getAccessTokenUsingRefreshToken() {
    String url = "https://kauth.kakao.com/oauth/token";

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
    link.put("web_url", "https://naver.com");
    link.put("mobile_web_url", "https://naver.com");

    JSONObject templateObject = new JSONObject();
    templateObject.put("object_type", "text");
    templateObject.put("text", text);
    templateObject.put("link", link);
    templateObject.put("button_title", "click this");
    return templateObject.toString();
  }
}
