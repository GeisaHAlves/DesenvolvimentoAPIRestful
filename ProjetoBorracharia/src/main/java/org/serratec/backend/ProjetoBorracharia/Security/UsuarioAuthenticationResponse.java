package org.serratec.backend.ProjetoBorracharia.Security;

public class UsuarioAuthenticationResponse {

	 private final String token;

     public String getToken() {
         return token;

     }

     public UsuarioAuthenticationResponse(String token) {
         super();
         this.token = token;
     }

}
