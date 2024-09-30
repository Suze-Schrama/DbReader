package be.vdab.dbreader.todo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Embeddable
public class ToDo {
    private String tekst;
    private int prioriteit;
    private LocalDateTime gemaakt;

    public ToDo(String tekst, int prioriteit) {
        this.tekst = tekst;
        this.prioriteit = prioriteit;
        this.gemaakt = LocalDateTime.now();
    }

    protected ToDo() {}

    public String getTekst() {
        return tekst;
    }

    public int getPrioriteit() {
        return prioriteit;
    }

    public LocalDateTime getGemaakt() {
        return gemaakt;
    }
}
