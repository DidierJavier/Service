package co.com.sofka.stepdefinition;

import co.com.sofka.model.User;
import co.com.sofka.setup.ServiceSetup;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import javax.jws.soap.SOAPBinding;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class LoginStepDefinition extends ServiceSetup {

    private static final Logger LOGGER = Logger.getLogger(LoginStepDefinition.class);

    private Response response;

    private RequestSpecification requestSpecification;
    @Dado("que el usuario está en la página de inicio de sesión con el correo de usuario {string} y la contraseña {string}")
    public void queElUsuarioEstaEnLaPaginaDeInicioDeSesionConElCorreoDeUsuarioYLaContrasena(String user, String password) {
        try{
            generalSetup();
            User userObject = new User();
            userObject.setEmailUser(user);
            userObject.setPasswordUser(password);
            requestSpecification = given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(userObject.toString());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }
    @Cuando("el usuario hace una petición de inicio de sesión")
    public void elUsuarioHaceUnaPeticionDeInicioDeSesion() {
        try {
            response = requestSpecification
                    .when()
                    .log().all()
                    .post(LOGIN_RESOURCE);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }
    @Entonces("el usuario deberá ver un código de respuesta exitoso y un token de respuesta")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaExitosoYUnTokenDeRespuesta() {
        String tokenString = response.asString().substring(10, 27);
        response.then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());
                Assertions.assertEquals(17, tokenString.length());

    }
}
