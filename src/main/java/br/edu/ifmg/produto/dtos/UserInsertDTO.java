package br.edu.ifmg.produto.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserInsertDTO extends UserDTO {

    @NotBlank
    @Size(min = 2, max = 20)
    private String password;

    public UserInsertDTO() {
        super();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
