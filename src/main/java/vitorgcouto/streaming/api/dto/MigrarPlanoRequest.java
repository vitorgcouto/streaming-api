package vitorgcouto.streaming.api.dto;

import jakarta.validation.constraints.NotNull;
import vitorgcouto.streaming.api.domain.Plano;

public record MigrarPlanoRequest(@NotNull Plano plano) {
}
