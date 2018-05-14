package uk.gov.hmcts.reform.feature;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
@TestPropertySource("classpath:application.properties")
public abstract class SmokeTestSuite {

    @Value("${test-url}")
    protected String testUrl;

    protected static final String SYNTHETIC_SOURCE_HEADER_VALUE = "Feature Toggle Web Console Smoke Test";

    protected RequestSpecification getCommonRequestSpec() {
        return RestAssured
            .given()
            .baseUri(testUrl)
            .relaxedHTTPSValidation()
            .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
