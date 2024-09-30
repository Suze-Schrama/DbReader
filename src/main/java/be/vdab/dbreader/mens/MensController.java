package be.vdab.dbreader.mens;

import be.vdab.dbreader.todo.NieuweToDo;
import be.vdab.dbreader.todo.ToDo;
import jakarta.validation.Valid;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Stream;
@CrossOrigin
@RestController
@RequestMapping("mensen")
@ExposesResourceFor(Mens.class)
public class MensController {
    private final MensService mensService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Mens> links;

    public MensController(MensService mensService, EntityLinks links) {
        this.mensService = mensService;
        this.links = links.forType(Mens.class, Mens::getId);
    }

    private record ToDosVanEenMens(String tekst, int prioriteit, LocalDateTime gemaakt) {
        ToDosVanEenMens(ToDo toDo) {
            this(toDo.getTekst(), toDo.getPrioriteit(), toDo.getGemaakt());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    HttpHeaders create(@RequestBody @Valid NieuweMens nieuweMens) {
        var mens = mensService.create(nieuweMens);
        var headers = new HttpHeaders();
        headers.setLocation(links.linkToItemResource(mens).toUri());
        return headers;
    }

    @PostMapping("{id}/todos")
    @ResponseStatus(HttpStatus.OK)
    public void voegToDoToe(@PathVariable long id, @Valid @RequestBody NieuweToDo nieuweToDo) {
        var toDo = new ToDo(nieuweToDo.tekst(), nieuweToDo.prioriteit());
        mensService.voegToDoToe(id, toDo);
    }

    @GetMapping("{id}/todos")
    Stream <ToDosVanEenMens> findToDos(@PathVariable long id) {
        return mensService.findById(id)
                .orElseThrow(MensNietGevondenException::new)
                .getToDos()
                .stream()
                .map(ToDosVanEenMens::new);
    }

    @GetMapping("{id}")
    Mens findMensById(@PathVariable long id){
        return mensService.findById(id).orElseThrow(MensNietGevondenException::new);
    }
}
