package hands.on.undo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class VehicleResourceTest {

    @Test
    public void testGetVehicleEndpoint1() {
        given()
          .when().get("/api/vehicle/B079V9ZDNY")
          .then()
             .statusCode(200)
             .body(is(not(nullValue())));
    }

    @Test
    public void testGetVehicleEndpoint2() {
        given()
          .when().get("/api/vehicle/UNKNOWN_VIN17")
          .then()
             .statusCode(200);
    }
}