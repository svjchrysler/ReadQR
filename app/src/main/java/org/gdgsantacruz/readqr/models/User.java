package org.gdgsantacruz.readqr.models;

/**
 * Created by svjchrysler on 3/3/17.
 */

public class User {
    private String name;
    private boolean estado;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
