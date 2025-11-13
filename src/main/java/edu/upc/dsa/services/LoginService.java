package edu.upc.dsa.services;

import edu.upc.dsa.models.LoginCredentials;
import edu.upc.dsa.models.User;
import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/auth", description = "Endpoint to Auth Service")
@Path("/auth")
public class LoginService {

    private UserManager um;

    public LoginService() {
        this.um = UserManagerImpl.getInstance();

        // Si vols, aquí també podries afegir usuaris de prova,
        // però ja els hem afegit al constructor de UserManagerImpl.
    }

    @POST
    @ApiOperation(value = "Login d’usuari", notes = "Comprova username i password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login correcte", response = User.class),
            @ApiResponse(code = 400, message = "Falten camps"),
            @ApiResponse(code = 401, message = "Credencials incorrectes")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginCredentials credentials) {

        if (credentials == null ||
                credentials.getUsername() == null ||
                credentials.getPassword() == null) {
            return Response.status(400)
                    .entity("{\"message\":\"Falten camps username o password\"}")
                    .build();
        }

        boolean ok = um.validateUser(credentials.getUsername(), credentials.getPassword());

        if (ok) {
            User u = um.getUser(credentials.getUsername());
            return Response.status(200)
                    .entity(u)   // pots retornar l'usuari o només un missatge
                    .build();
        }
        else {
            return Response.status(401)
                    .entity("{\"message\":\"Usuari o contrasenya incorrectes\"}")
                    .build();
        }
    }
}

