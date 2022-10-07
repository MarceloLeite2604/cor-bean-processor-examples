package com.figtreelake.multiplechains.service.contentverifier.link;

import com.figtreelake.multiplechains.service.contentverifier.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JsonContentVerifier extends AbstractContentVerifier {

  @Override
  protected Optional<ContentType> doVerify(String content) {
    try {
      new JSONObject(content);
      return Optional.of(ContentType.JSON);
    } catch (JSONException exception) {
      return Optional.empty();
    }
  }
}
