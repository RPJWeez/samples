/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.paul.sample;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author pauli
 */
@ApplicationPath("")
@DeclareRoles({"foo"})
public class SampleApplication extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        Set classes = new HashSet<>();
        classes.add(SampleResource.class);
        return classes;
    }
    
    
}
