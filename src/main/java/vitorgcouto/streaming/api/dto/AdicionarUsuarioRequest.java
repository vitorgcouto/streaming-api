package vitorgcouto.streaming.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import vitorgcouto.streaming.api.domain.Perfil;

public record AdicionarUsuarioRequest(@NotBlank String nome,
                                      @NotBlank @Email String email,
                                      @NotBlank @Size(min = 6) String senha,
                                      @NotNull Perfil perfil) {
}
