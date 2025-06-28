package com.codesa.backend.specification;

import com.codesa.backend.model.Persona;
import org.springframework.data.jpa.domain.Specification;

public class PersonaSpecification {

    public static Specification<Persona> nombreContiene(String nombre) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
    }

    public static Specification<Persona> apellidoContiene(String apellido) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("apellido")), "%" + apellido.toLowerCase() + "%");
    }

    public static Specification<Persona> emailContiene(String email) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }
}
