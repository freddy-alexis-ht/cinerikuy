package com.cinerikuy.movie.controller;

import com.cinerikuy.movie.entity.*;
import com.cinerikuy.movie.service.AdminService;
import com.cinerikuy.movie.exception.AdminException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /** POR CUESTIONES DE INTEGRIDAD DE LA DATA, EL BORRADO FÍSICO NO ES POSIBLE, SOLO BORRADO LÓGICO */

    /** MOVIE */

    @Tag(name = "admin-movie")
    @Operation(summary = "Recupera todas las películas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas las películas recuperadas con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay películas en la base de datos", content = @Content)})
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> movieFindAll() throws AdminException {
        List<Movie> list = adminService.movieFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay películas en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-movie")
    @Operation(summary = "Recupera 1 película por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Película recuperada con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe película con ese 'id'", content = @Content)})
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> movieFindById(@PathVariable long id) throws AdminException {
        Movie movie = adminService.movieFindById(id);
        if(movie == null)
            throw new AdminException("C001", "La película buscada no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(movie);
    }

    @Tag(name = "admin-movie")
    @Operation(summary = "Guarda/Actualiza la película enviada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Película guardada/actualizada con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar la película enviada", content = @Content)})
    @PostMapping("/movies")
    public ResponseEntity<Movie> movieSaveUpdate(@RequestBody Movie input) throws AdminException {
        Movie save;
        try{
            save = adminService.movieSaveUpdate(input);
        }catch (Exception e) {
            throw new AdminException("C001", "Error en la data enviada.", HttpStatus.PRECONDITION_FAILED);
        }
        return ResponseEntity.ok(save);
    }


    /** GENRE */

    @Tag(name = "admin-genre")
    @Operation(summary = "Recupera todos los géneros de películas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los géneros recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay géneros en la base de datos", content = @Content)})
    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> genreFindAll() throws AdminException {
        List<Genre> list = adminService.genreFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay géneros en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-genre")
    @Operation(summary = "Recupera 1 género por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Género recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe género con ese 'id'", content = @Content)})
    @GetMapping("/genres/{id}")
    public ResponseEntity<Genre> genreFindById(@PathVariable long id) throws AdminException {
        Genre genre = adminService.genreFindById(id);
        if(genre == null)
            throw new AdminException("C001", "El género buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(genre);
    }

    @Tag(name = "admin-genre")
    @Operation(summary = "Guarda/Actualiza el género enviado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Género guardado/actualizado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar el género enviado", content = @Content)})
    @PostMapping("/genres")
    public ResponseEntity<Genre> genreSaveUpdate(@RequestBody Genre input) throws AdminException {
        String genreNameSend = input.getGenre();
        List<Genre> genres = this.genreFindAll().getBody();
        List<Genre> exist = genres.stream()
                .filter(c -> c.getGenre().equalsIgnoreCase(genreNameSend)).collect(Collectors.toList());
        if(!exist.isEmpty())
            throw new AdminException("C001", "El género enviado ya está registrado.", HttpStatus.PRECONDITION_FAILED);
        Genre save = adminService.genreSaveUpdate(input);
        return ResponseEntity.ok(save);
    }

    /** LANGUAGE */

    @Tag(name = "admin-language")
    @Operation(summary = "Recupera todos los idiomas de las películas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los idiomas recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay idiomas en la base de datos", content = @Content)})
    @GetMapping("/languages")
    public ResponseEntity<List<Language>> languageFindAll() throws AdminException {
        List<Language> list = adminService.languageFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay idiomas en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-language")
    @Operation(summary = "Recupera 1 idioma por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Idioma recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe idioma con ese 'id'", content = @Content)})
    @GetMapping("/languages/{id}")
    public ResponseEntity<Language> languageFindById(@PathVariable long id) throws AdminException {
        Language language = adminService.languageFindById(id);
        if(language == null)
            throw new AdminException("C001", "El idioma buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(language);
    }

    @Tag(name = "admin-language")
    @Operation(summary = "Guarda/Actualiza el idioma enviado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Idioma guardado/actualizado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar el idioma enviado", content = @Content)})
    @PostMapping("/languages")
    public ResponseEntity<Language> languageSaveUpdate(@RequestBody Language input) throws AdminException {
        String languageNameSend = input.getLanguage();
        List<Language> languages = this.languageFindAll().getBody();
        List<Language> exist = languages.stream()
                .filter(c -> c.getLanguage().equalsIgnoreCase(languageNameSend)).collect(Collectors.toList());
        if(!exist.isEmpty())
            throw new AdminException("C001", "El idioma enviadao ya está registrado.", HttpStatus.PRECONDITION_FAILED);
        Language save = adminService.languageSaveUpdate(input);
        return ResponseEntity.ok(save);
    }

    /** SITUATION */

    @Tag(name = "admin-situation")
    @Operation(summary = "Recupera todas las situaciones posibles en una película: Pasado, Estreno, Próximo...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los registros de situaciones recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay registro de situación en la base de datos", content = @Content)})
    @GetMapping("/situations")
    public ResponseEntity<List<Situation>> situationFindAll() throws AdminException {
        List<Situation> list = adminService.situationFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay registros de situación en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-situation")
    @Operation(summary = "Recupera 1 registro de situación por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de situación recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe registro de situación con ese 'id'", content = @Content)})
    @GetMapping("/situations/{id}")
    public ResponseEntity<Situation> situationFindById(@PathVariable long id) throws AdminException {
        Situation situation = adminService.situationFindById(id);
        if(situation == null)
            throw new AdminException("C001", "El registro de situación buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(situation);
    }

    @Tag(name = "admin-situation")
    @Operation(summary = "Guarda/Actualiza el registro de situación enviado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de situación guardado/actualizado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar la situación enviada", content = @Content)})
    @PostMapping("/situations")
    public ResponseEntity<Situation> situationSaveUpdate(@RequestBody Situation input) throws AdminException {
        String situationNameSend = input.getSituation();
        List<Situation> situations = this.situationFindAll().getBody();
        List<Situation> exist = situations.stream()
                .filter(c -> c.getSituation().equalsIgnoreCase(situationNameSend)).collect(Collectors.toList());
        if(!exist.isEmpty())
            throw new AdminException("C001", "El registro de situación enviado ya está registrado.", HttpStatus.PRECONDITION_FAILED);
        Situation save = adminService.situationSaveUpdate(input);
        return ResponseEntity.ok(save);
    }

    /** VOTE */

    @Tag(name = "admin-vote")
    @Operation(summary = "Recupera los posibles estados de una película en cuanto al voto: Votación, No-Votación...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los estados de votos recuperados con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No hay estado de voto en la base de datos", content = @Content)})
    @GetMapping("/vote-states")
    public ResponseEntity<List<Vote>> voteFindAll() throws AdminException {
        List<Vote> list = adminService.voteFindAll();
        if(list == null)
            throw new AdminException("C001", "No hay estados de voto en la base de datos.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(list);
    }

    @Tag(name = "admin-vote")
    @Operation(summary = "Recupera 1 estado de voto por su 'id'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de voto recuperado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "No existe estado de voto con ese 'id'", content = @Content)})
    @GetMapping("/vote-states/{id}")
    public ResponseEntity<Vote> voteFindById(@PathVariable long id) throws AdminException {
        Vote vote = adminService.voteFindById(id);
        if(vote == null)
            throw new AdminException("C001", "El estado de voto buscado no existe.", HttpStatus.PRECONDITION_FAILED);
        return ResponseEntity.ok(vote);
    }

    @Tag(name = "admin-vote") // TODO .. el admin no debería poder guardar o actualizar
    @Operation(summary = "Guarda/Actualiza el estado de voto enviado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de voto guardado/actualizado con éxito", content = @Content),
            @ApiResponse(responseCode = "412", description = "Error al momento de guardar/actualizar el estado de voto enviado", content = @Content)})
    @PostMapping("/vote-states")
    public ResponseEntity<Vote> voteSaveUpdate(@RequestBody Vote input) throws AdminException {
        String voteNameSend = input.getVote();
        List<Vote> votes = this.voteFindAll().getBody();
        List<Vote> exist = votes.stream()
                .filter(c -> c.getVote().equalsIgnoreCase(voteNameSend)).collect(Collectors.toList());
        if(!exist.isEmpty())
            throw new AdminException("C001", "El estado de voto enviado ya está registrado.", HttpStatus.PRECONDITION_FAILED);
        Vote save = adminService.voteSaveUpdate(input);
        return ResponseEntity.ok(save);
    }

}
