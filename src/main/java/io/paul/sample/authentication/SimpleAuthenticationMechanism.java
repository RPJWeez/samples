/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.paul.sample.authentication;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.glassfish.soteria.Utils.notNull;

/**
 * This is a sample HttpAuthenticationMechanism to try out the features of
 * JSR375. It gets a username and password from the HTTP request parameters and
 * sends them to the SimpleIdentityStore for validation.
 *
 * @author PI
 */
@ApplicationScoped
public class SimpleAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    SimpleIdentityStore is;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        UsernamePasswordCredential credential = new UsernamePasswordCredential(name, password);

        if (notNull(name, password)) {
            CredentialValidationResult result = is.validate(credential);
            if (CredentialValidationResult.Status.VALID == result.getStatus()) {
                //tell app server to continue to request
                return httpMessageContext.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
            }

        }
        
        //respond HTTP 401 is validation failed, or credentials aren't present
        return httpMessageContext.responseUnauthorized();
    }

}
