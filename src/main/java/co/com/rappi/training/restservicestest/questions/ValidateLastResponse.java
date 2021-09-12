package co.com.rappi.training.restservicestest.questions;

import co.com.rappi.training.restservicestest.models.ResourceData;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidateLastResponse implements Question<ResourceData> {

  public static ValidateLastResponse forResource() {
    return new ValidateLastResponse();
  }

  @Override
  public ResourceData answeredBy(Actor actor) {
    ResourceData resource = SerenityRest.lastResponse()
        .jsonPath()
        .getObject("data", ResourceData.class);
    return resource;
  }
}