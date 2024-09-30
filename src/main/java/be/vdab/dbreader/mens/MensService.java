package be.vdab.dbreader.mens;

import be.vdab.dbreader.todo.ToDo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MensService {
    private final MensRepository mensRepository;

    public MensService(MensRepository mensRepository) {
        this.mensRepository = mensRepository;
    }

    @Transactional
    public Mens create(NieuweMens nieuweMens){
        var mens = new Mens(nieuweMens.voornaam(), nieuweMens.familienaam());
        mensRepository.save(mens);
        return mens;
    }

    @Transactional
    public void voegToDoToe(long id, ToDo toDo){
        var mens = mensRepository.findById(id).orElseThrow(MensNietGevondenException::new);
        mens.voegToDoToe(toDo);
    }

    Optional<Mens> findById(long id){
        return mensRepository.findById(id);
    }
}
