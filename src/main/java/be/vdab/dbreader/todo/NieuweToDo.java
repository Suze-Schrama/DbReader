package be.vdab.dbreader.todo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NieuweToDo(@NotBlank String tekst, @NotNull @Min(1)@Max(10) int prioriteit) {
}
