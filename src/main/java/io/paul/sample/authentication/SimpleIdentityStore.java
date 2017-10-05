/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.paul.sample.authentication;

import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author pauli
 */
@ApplicationScoped
public class SimpleIdentityStore implements IdentityStore{
    @Override
    public CredentialValidationResult validate(Credential credential) {
        System.out.println("io.paul.sample.authentication.SimpleIdentityStore.validate()");
        UsernamePasswordCredential uCredential = (UsernamePasswordCredential) credential;
        
        if (uCredential.compareTo("paul", "secret")) {
        
           return new CredentialValidationResult("paul",new HashSet<>(asList("foo")));
        }

        return INVALID_RESULT;
    }
    
}
