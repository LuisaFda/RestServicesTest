package co.com.rappi.training.restservicestest.utils;

import co.com.rappi.training.restservicestest.models.ResourceData;
import co.com.rappi.training.restservicestest.models.UserData;
import net.serenitybdd.rest.SerenityRest;

public class GetInformation {

  public static ResourceData getSingleResource() {
    ResourceData data = new ResourceData("2", "fuchsia rose", "2001", "#C74375", "17-2031");

    if (SerenityRest.lastResponse().getStatusCode() == 404) {
      data = null;
    }
    return data;
  }

  public static String getUserData() {
    String respuesta = "token";
    if (SerenityRest.lastResponse().getStatusCode() == 400) {
      respuesta = "error";
    }
    return respuesta;
  }

}
