package be.vdab.dbreader.mens;

import jakarta.validation.constraints.NotBlank;

public record NieuweMens(@NotBlank String voornaam, @NotBlank String familienaam) {
}
