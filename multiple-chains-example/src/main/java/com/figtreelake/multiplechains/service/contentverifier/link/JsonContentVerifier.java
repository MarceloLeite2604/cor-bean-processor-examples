package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonContentVerifier extends AbstractContentVerifier {

  @Override
  protected ContentType doVerify(String content) {
    try {
      new JSONObject(content);
      return ContentType.JSON;
    } catch (JSONException exception) {
      return ContentType.UNKNOWN;
    }
  }
}
