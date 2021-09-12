package co.com.rappi.training.restservicestest.stepdefinitions;

import static co.com.rappi.training.restservicestest.utils.GeneralConstans.ACTOR;
import static co.com.rappi.training.restservicestest.utils.GeneralConstans.BASE_URL;
import static co.com.rappi.training.restservicestest.utils.GeneralConstans.FIELD_UPDATE;
import static co.com.rappi.training.restservicestest.utils.GeneralConstans.MESSAGE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import co.com.rappi.training.restservicestest.exceptions.ValidationException;
import co.com.rappi.training.restservicestest.models.UpdateUser;
import co.com.rappi.training.restservicestest.models.UserData;
import co.com.rappi.training.restservicestest.questions.ValidateLastResponse;
import co.com.rappi.training.restservicestest.questions.ValidateUpdateUser;
import co.com.rappi.training.restservicestest.utils.GetInformation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.hamcrest.Matchers;

public class ReqresApiStepDefinition {

  UserData userData;
  UpdateUser userUpdate;

  @Given("the user consumes the service")
  public void theUserConsumesTheService() {
    OnStage.setTheStage(new OnlineCast());
    OnStage.theActorCalled(ACTOR).whoCan(
        CallAnApi.at(BASE_URL));
  }

  @When("He converts the information consulted the service {} for the user {}")
  public void heConvertsTheInformationConsulted(String path, String user) {
    OnStage.theActorInTheSpotlight().attemptsTo(
        Get.resource(path + user).with(requestSpecification -> requestSpecification.contentType(
            ContentType.JSON))
    );
  }

  @Then("it will validate the result {}")
  public void itWillValidateTheResult(int codeResponse) {
    OnStage.theActorInTheSpotlight()
        .should(
            seeThatResponse(
                MESSAGE, response -> response.statusCode(codeResponse))
                .orComplainWith(ValidationException.class, ValidationException.ERROR_RESPONSE_CODE),
            seeThat(
                ValidateLastResponse.forResource(), Matchers.is(GetInformation.getSingleResource()))
                .orComplainWith(ValidationException.class, ValidationException.ERROR_VALIDATION)
        );
  }

  @When("the user consults the login of the application in {} with {} and {}")
  public void theUserConsultsTheLoginOfTheApplicationInApiUnknownWithAnd(String path, String email,
      String password) {
    userData = new UserData(email, password);
    OnStage.theActorInTheSpotlight().attemptsTo(
        Post.to(path)
            .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                .body(userData))
    );
    System.out.println(userData.toString());
  }

  @Then("it will validate the {} and the message")
  public void itWillValidateTheAndTheMessage(int codeResponse) {
    OnStage.theActorInTheSpotlight()
        .should(
            seeThatResponse(
                MESSAGE, response -> response.statusCode(codeResponse))
                .orComplainWith(ValidationException.class, ValidationException.ERROR_RESPONSE_CODE),
            seeThatResponse(
                MESSAGE,
                response -> response.body(Matchers.containsString(GetInformation.getUserData())))
                .orComplainWith(ValidationException.class, ValidationException.ERROR_VALIDATION)
        );
  }

  @When("the user update a user of the application in {} with {} and {}")
  public void theUserUpdateAUserOfTheApplicationInApiLoginWithEmailAndPassword(String path,
      String name,
      String job) {
    userUpdate = new UpdateUser(name, job);
    OnStage.theActorInTheSpotlight().attemptsTo(
        Put.to(path)
            .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                .body(userUpdate))
    );
    System.out.println(userUpdate.toString());
  }

  @Then("it will validate the {} and the the changes")
  public void itWillValidateTheAndTheTheChanges(int codeResponse) {
    OnStage.theActorInTheSpotlight()
        .should(
            seeThatResponse(
                MESSAGE, response -> response.statusCode(codeResponse))
                .orComplainWith(ValidationException.class, ValidationException.ERROR_RESPONSE_CODE),
            seeThat(
                ValidateUpdateUser.inTheService(), Matchers.is(userUpdate))
                .orComplainWith(ValidationException.class, ValidationException.ERROR_VALIDATION),
            seeThatResponse(
                MESSAGE,
                response -> response.body(Matchers.containsString(FIELD_UPDATE)))
                .orComplainWith(ValidationException.class, ValidationException.ERROR_VALIDATION)
        );
  }
}