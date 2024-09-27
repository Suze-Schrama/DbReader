package be.vdab.dbreader;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "mensen")
public class Mens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String voornaam;
    private String familienaam;

    @ElementCollection
    @CollectionTable(name = "todos", joinColumns = @JoinColumn(name = "mensId"))
    @OrderBy("gemaakt")
    private Set<ToDo> toDos = new LinkedHashSet<>();

    public Mens(String voornaam, String familienaam) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
    }

    protected Mens() {}

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Set<ToDo> getToDos() {
        return Collections.unmodifiableSet(toDos);
    }

    public void voegToDoToe(ToDo toDo) {
        toDos.add(toDo);
    }

}

