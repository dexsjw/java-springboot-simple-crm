package sg.edu.ntu.simple_crm.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.edu.ntu.simple_crm.data.Interaction;
import sg.edu.ntu.simple_crm.services.InteractionService;

@RestController
@RequestMapping("/interactions")
public class InteractionController {

    private InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Interaction> saveInteraction(@Valid @RequestBody Interaction interaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(interactionService.saveInteraction(interaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interaction> findInteractionById(@PathVariable Long id) {
        return ResponseEntity.ok(interactionService.findInteractionById(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Interaction>> findAllInteraction() {
        return ResponseEntity.ok(interactionService.findAllInteraction());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interaction> updateInteraction(@PathVariable Long id, @Valid @RequestBody Interaction interaction) {
        return ResponseEntity.ok(interactionService.updateInteractionById(id, interaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInteractionById(@PathVariable Long id) {
        interactionService.deleteInteractionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
