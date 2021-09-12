package co.com.rappi.training.restservicestest.questions;

import co.com.rappi.training.restservicestest.models.UpdateUser;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidateUpdateUser implements Question<UpdateUser> {

  public static ValidateUpdateUser inTheService() {
    return new ValidateUpdateUser();
  }

  @Override
  public UpdateUser answeredBy(Actor actor) {
    UpdateUser user = SerenityRest.lastResponse()
        .jsonPath()
        .getObject("", UpdateUser.class);
    System.out.println(user.toString());
    return user;
  }

}
