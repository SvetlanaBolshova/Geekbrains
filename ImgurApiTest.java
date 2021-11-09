

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class ImgurApiTest {

        @BeforeAll
        static void beforeAll() {
            RestAssured.baseURI = ImgurApiParams.API_URL + "/" + ImgurApiParams.API_VERSION;
        }

        @DisplayName("Тест на получение базовой информации об аккаунте")
        @Test
        @Order(1)
        void testAccountBase() {
            String url = "account/" + "bolshova99";
            given().when()
                    .auth()
                    .oauth2(ImgurApiParams.TOKEN)
                    .log()
                    .all()
                    .expect()
                    .statusCode(is(200))
                    .body("success", is(true))
                    .body("status", is(200))
                    .body("data.reputation_name", is("Neutral"))
                    .body("data.reputation", is(0))
                    .log()
                    .all()
                    .when()
                    .get(url);
        }

        @DisplayName("Тест обновления информации о картинке")
        @Test
        @Order(2)
        void testUpdateImageInformation() {
            String url = "image/" + "fhT8GDx";
//        given().get("").body().jsonPath().getString("data.id")
            given().when()
                    .auth()
                    .oauth2(ImgurApiParams.TOKEN)
                    .log()
                    .all()
                    .formParam("title", "Water")
                    .formParam("description", "Just a simple mem")
                    .expect()
                    .log()
                    .all()
                    .statusCode(is(200))
                    .body("success", is(true))
                    .body("data", is(true))
                    .when()
                    .post(url);
        }
    }