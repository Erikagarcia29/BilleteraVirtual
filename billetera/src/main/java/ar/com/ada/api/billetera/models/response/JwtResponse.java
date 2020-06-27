package ar.com.ada.code.billetera_virtual_back.models.response;

/**
 * JwtResponse
 */
public class JwtResponse {

    private final String jwttoken;

    public JwtResponse(String jwttoken) {

        this.jwttoken = jwttoken;

    }

    public String getToken() {

        return this.jwttoken;

    }

} 