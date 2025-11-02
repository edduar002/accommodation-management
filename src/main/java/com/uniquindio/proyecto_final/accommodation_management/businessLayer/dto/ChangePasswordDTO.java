package com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto;

public class ChangePasswordDTO {

    // Contraseña actual ingresada por el usuario (para validar)
    private String oldPassword;

    // Nueva contraseña que el usuario desea establecer
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}