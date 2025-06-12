package br.edu.ifmg.produto.dtos;

import jakarta.validation.constraints.NotBlank;

public class NewPasswordDTO {

    @NotBlank(message = "Campo Requerido")
    private String newPassword;
    @NotBlank(message = "Campo Requerido")
    private String token;

    public NewPasswordDTO() {}

    public NewPasswordDTO(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "NewPasswordDTO{" +
                "newPassword='" + newPassword + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
