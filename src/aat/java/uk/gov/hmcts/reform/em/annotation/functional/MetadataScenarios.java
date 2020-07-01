package uk.gov.hmcts.reform.em.annotation.functional;

import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import uk.gov.hmcts.reform.em.EmTestConfig;
import uk.gov.hmcts.reform.em.annotation.service.dto.MetadataDto;
import uk.gov.hmcts.reform.em.annotation.testutil.TestUtil;

@SpringBootTest(classes = {TestUtil.class, EmTestConfig.class})
@PropertySource(value = "classpath:application.yml")
@RunWith(SpringIntegrationSerenityRunner.class)
public class MetadataScenarios {

    @Autowired
    TestUtil testUtil;

    @Value("${test.url}")
    String testUrl;

    @Test
    public void testSaveSuccessCreate() {

        MetadataDto metadataDto = testUtil.createMetadataDto();
        JSONObject jsonObject = new JSONObject(metadataDto);

        testUtil.authRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .body(jsonObject)
            .request("POST", testUrl + "/api/metadata/")
            .then()
            .statusCode(201);
    }

    @Test
    public void testSaveSuccessUpdate() {

        MetadataDto metadataDto = testUtil.createMetadataDto();
        JSONObject jsonObject = new JSONObject(metadataDto);

        testUtil.authRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .body(jsonObject)
            .request("POST", testUrl + "/api/metadata/")
            .then()
            .statusCode(201);

        metadataDto.setRotationAngle(180);
        JSONObject updateJson = new JSONObject(metadataDto);

        testUtil.authRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .body(updateJson)
            .request("POST", testUrl + "/api/metadata/")
            .then()
            .statusCode(201);
    }

    @Test
    public void testFindByDocumentIdSuccess() {

        MetadataDto metadataDto = testUtil.createMetadataDto();
        JSONObject jsonObject = new JSONObject(metadataDto);

        testUtil.authRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .body(jsonObject)
            .request("POST", testUrl + "/api/metadata/")
            .then()
            .statusCode(201);

        testUtil.authRequest()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .request("GET", testUrl + "/api/metadata/" + metadataDto.getDocumentId())
            .then()
            .statusCode(200);

    }
}
