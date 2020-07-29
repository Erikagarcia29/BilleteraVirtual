package ar.com.ada.api.billetera.models.response;

import java.util.ArrayList;
import java.util.List;

//registration response()
public class RegistrationResponse {
    public boolean isOk = false;
    public String message = "";
    public Integer userId ;
    public List<ErrorResponseItem> errors = new ArrayList<>();
}