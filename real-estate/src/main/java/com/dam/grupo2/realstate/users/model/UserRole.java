package com.dam.grupo2.realstate.users.model;

public enum UserRole {

     ADMIN("ADMIN"), PROPIETARIO("PROPIETARIO"), GESTOR("GESTOR");

     private String valor;

     private UserRole (String valor) {this.valor=valor;}
     public String getValor (){return valor;}
    public void setValor (String valor) {this.valor=valor;}
}
