package co.com.rappi.training.restservicestest.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/reqres_api.feature",
    glue = "co.com.rappi.training.restservicestest.stepdefinitions",
    snippets = CucumberOptions.SnippetType.CAMELCASE)
public class ReqresApiRunner {
}