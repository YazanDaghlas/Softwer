package YazanSystem;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features ="use_cases",
        plugin = { "summary", "html: target/cucumber/wikipedia.html"},
        monochrome = true,
        snippets =CucumberOptions.SnippetType.CAMELCASE,
        glue={"YazanSystem"})


public class AcceptanceTest {


}
