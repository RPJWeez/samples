/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.paul.sample;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * This is a Hello World API that demonstrates a secured resource
 * 
 * @author PI
 */
@Path("hello")
@DenyAll
public class SampleResource {
    
    //TODO no impl to inject so this is null.. Maybe a Payara/Glassfish problem?
    //@Inject
    //private SecurityContext securityContext;
    
    @GET
    @RolesAllowed({"foo"})
    public String hello() {
        return "Hello, "; //+ securityContext.getCallerPrincipal();
    }
}
