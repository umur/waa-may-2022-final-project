package com.pmp.server.security.service.impl;

import java.util.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.pmp.server.domain.User;
import com.pmp.server.dto.LoginDTO;
import com.pmp.server.dto.ResetPasswordDTO;
import com.pmp.server.dto.UpdateUserDTO;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.exceptionHandler.exceptions.CustomErrorException;
import com.pmp.server.repo.UserRepo;
import com.pmp.server.service.impl.UserServiceImpl;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.pmp.server.utils.constants.ResponseMessageConstants.*;

@Service
public class AuthServiceImpl {

  private final UserServiceImpl userService;
  private final UserRepo userRepo;
  private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

  private static String authServerUrl = "http://localhost:8080";
  private static String realm = "pmp-realm";
  private static String clientId = "pmp-client";
  private String role = "ROLE_LANDLORD";
  //Get client secret from the Keycloak admin console (in the credential tab)
  private static String clientSecret = "Bhw120rFFBSVLciCuictWw5wOuSbJmu2";

  public AuthServiceImpl(UserServiceImpl userService, UserRepo userRepo) {
    this.userService = userService;
    this.userRepo = userRepo;
  }

  public ResponseMessage registerUser(UserDTO userDTO) {
    Keycloak keycloak = getKeyCloak();


    // save user in authentication server
    UserRepresentation user = new UserRepresentation();
    user.setEnabled(true);
    user.setUsername(userDTO.getEmail());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());

    // Get realm
    RealmResource realmResource = keycloak.realm(realm);
    UsersResource usersResource = realmResource.users();

    Response response = usersResource.create(user);

    if (response.getStatus() == 201) {

      String userId = CreatedResponseUtil.getCreatedId(response);
      log.info("Created userId {}", userId);

      // save user in resource server
      User userRS = new User();
      userRS.setEmail(userDTO.getEmail());
      userRS.setFirstName(userDTO.getFirstName());
      userRS.setLastName(userDTO.getLastName());
      userRS.setGender(userDTO.getGender());
      userRS.setPassword(userDTO.getPassword());
      userRS.setId(UUID.fromString(userId));
      userRS.setActive(true);

      userService.saveUser(userRS);


      // create password credential
      CredentialRepresentation passwordCred = new CredentialRepresentation();
      passwordCred.setTemporary(false);
      passwordCred.setType(CredentialRepresentation.PASSWORD);
      passwordCred.setValue(userDTO.getPassword());

      UserResource userResource = usersResource.get(userId);

      // Set password credential
      userResource.resetPassword(passwordCred);

      // Get realm role student
      RoleRepresentation realmRoleUser = realmResource.roles().get(role).toRepresentation();

      // Assign realm role student to user
      userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));

      return new ResponseMessage(SUCCESSFUL_MESSAGE, HttpStatus.OK, userDTO);
    } else if (response.getStatus() == 409) {
      log.error("Conflicting data is present");
      throw new CustomErrorException(HttpStatus.valueOf(response.getStatus()), UNSUCCESSFUL_MESSAGE, "Conflicting data is present");
    } else {
      log.error("Internal Server error");
      throw new CustomErrorException(HttpStatus.valueOf(response.getStatus()), UNSUCCESSFUL_MESSAGE, "Internal Server error");
    }

  }


  public ResponseMessage login(LoginDTO loginDTO) {

    try {
      Map<String, Object> clientCredentials = new HashMap<>();
      clientCredentials.put("secret", clientSecret);
      clientCredentials.put("grant_type", "password");

      Configuration configuration =
        new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
      AuthzClient authzClient = AuthzClient.create(configuration);

      AccessTokenResponse response =
        authzClient.obtainAccessToken(loginDTO.getEmail(), loginDTO.getPassword());

      return new ResponseMessage(SUCCESSFUL_MESSAGE, HttpStatus.OK, response);

    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new CustomErrorException(HttpStatus.UNAUTHORIZED, null, "Invalid user credentials");
    }
  }


  public ResponseMessage updateUser(UUID id, UpdateUserDTO updateUserDTO) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication != null){
      if(authentication.getPrincipal() instanceof KeycloakPrincipal){
        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
        String uuid = kp.getKeycloakSecurityContext().getToken().getId();
      }
    }

    Keycloak keycloak = getKeyCloak();
    Optional<User> user = userRepo.findById(id);
    if (user.isPresent()) {
      User u = user.get();
      u.setLastName(updateUserDTO.getLastName());
      u.setFirstName(updateUserDTO.getFirstName());

      try {
        var userResource = keycloak.realm(realm).users().get(u.getId().toString());
        var userPresentation = userResource.toRepresentation();
        userPresentation.setFirstName(updateUserDTO.getFirstName());
        userPresentation.setLastName(updateUserDTO.getLastName());
        userResource.update(userPresentation);

        userService.saveUser(u);
        return new ResponseMessage(SUCCESSFUL_MESSAGE, HttpStatus.OK, updateUserDTO);

      } catch (Exception ex) {
        log.error(ex.getMessage());
        throw new CustomErrorException(HttpStatus.BAD_REQUEST, null, UNSUCCESSFUL_MESSAGE);
      }

    }
    log.error(DATA_NOT_FOUND_TO_UPDATE);
    throw new CustomErrorException(HttpStatus.BAD_REQUEST, null, DATA_NOT_FOUND_TO_UPDATE);


  }

  public ResponseMessage resetPassword(UUID id, ResetPasswordDTO resetPasswordDTO) {
    Keycloak keycloak = getKeyCloak();
    Optional<User> user = userRepo.findById(id);
    if (user.isPresent()) {
      try {
        var userResource = keycloak.realm(realm).users().get(id.toString());
        CredentialRepresentation cr = new CredentialRepresentation();
        cr.setType(CredentialRepresentation.PASSWORD);
        cr.setValue(resetPasswordDTO.getPassword());
        cr.setTemporary(false);
        userResource.resetPassword(cr);

        user.get().setPassword(resetPasswordDTO.getPassword());
        userService.saveUser(user.get());
        return new ResponseMessage(SUCCESSFULLY_UPDATED, HttpStatus.OK, "Password updated successfully");
      } catch (Exception e) {
        log.error(e.getMessage());
        throw new CustomErrorException(HttpStatus.BAD_REQUEST, null, UNSUCCESSFUL_MESSAGE);
      }

    }
    log.error(DATA_NOT_FOUND_TO_UPDATE);
    throw new CustomErrorException(HttpStatus.BAD_REQUEST, null, DATA_NOT_FOUND_TO_UPDATE);


  }

  public static Keycloak getKeyCloak() {
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
    return keycloak;

  }


}
