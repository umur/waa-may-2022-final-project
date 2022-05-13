package com.pmp.server.security.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;

import com.pmp.server.controller.AuthController;
import com.pmp.server.domain.User;
import com.pmp.server.dto.LoginDTO;
import com.pmp.server.service.impl.UserServiceImpl;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pmp.server.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

  private final UserServiceImpl userService;
  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  private String authServerUrl = "http://localhost:8080";
  private String realm = "pmp-realm";
  private String clientId = "pmp-client";
  private String role = "ROLE_LANDLORD";
  //Get client secret from the Keycloak admin console (in the credential tab)
  private String clientSecret = "Bhw120rFFBSVLciCuictWw5wOuSbJmu2";

  public AuthServiceImpl(UserServiceImpl userService) {
    this.userService = userService;
  }

  public UserDTO registerUser(UserDTO userDTO) {
    Keycloak keycloak = KeycloakBuilder.builder()
      .serverUrl(authServerUrl)
//      .grantType(OAuth2Constants.PASSWORD)
      .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
      .realm(realm)
      .clientId(clientId)
      .clientSecret(clientSecret)
//      .username("admin")
//      .password("admin")
      .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
      .build();

    keycloak.tokenManager().getAccessToken();

    // save user in resource server
    User userRS = new User();
    userRS.setEmail(userDTO.getEmail());
    userRS.setFirstName(userDTO.getFirstName());
    userRS.setLastName(userDTO.getLastName());
    userRS.setGender(userDTO.getGender());
    userRS.setPassword(userDTO.getPassword());
    userRS.setActive(true);

    userService.saveUser(userRS);

    // save user in authentication server
    UserRepresentation user = new UserRepresentation();
    user.setEnabled(true);
    user.setUsername(userDTO.getEmail());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());

    // Get realm
    RealmResource realmResource = keycloak.realm(realm);
    UsersResource usersRessource = realmResource.users();

    Response response = usersRessource.create(user);

    // pprajapati : log response from auth server'

    if (response.getStatus() == 201) {

      String userId = CreatedResponseUtil.getCreatedId(response);

      log.info("Created userId {}", userId);


      // create password credential
      CredentialRepresentation passwordCred = new CredentialRepresentation();
      passwordCred.setTemporary(false);
      passwordCred.setType(CredentialRepresentation.PASSWORD);
      passwordCred.setValue(userDTO.getPassword());

      UserResource userResource = usersRessource.get(userId);

      // Set password credential
      userResource.resetPassword(passwordCred);

      // Get realm role student
      RoleRepresentation realmRoleUser = realmResource.roles().get(role).toRepresentation();

      // Assign realm role student to user
      userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));
    }
    return userDTO;
  }


  public AccessTokenResponse login(LoginDTO loginDTO) {

    Map<String, Object> clientCredentials = new HashMap<>();
    clientCredentials.put("secret", clientSecret);
    clientCredentials.put("grant_type", "password");

    Configuration configuration =
      new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
    AuthzClient authzClient = AuthzClient.create(configuration);

    AccessTokenResponse response =
      authzClient.obtainAccessToken(loginDTO.getEmail(), loginDTO.getPassword());

    return response;
  }
}
