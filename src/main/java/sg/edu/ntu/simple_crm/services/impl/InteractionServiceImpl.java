package sg.edu.ntu.simple_crm.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.simple_crm.data.Interaction;
import sg.edu.ntu.simple_crm.exceptions.InteractionNotFoundException;
import sg.edu.ntu.simple_crm.repositories.InteractionRepository;
import sg.edu.ntu.simple_crm.services.InteractionService;

@Service
public class InteractionServiceImpl implements InteractionService {

    private InteractionRepository interactionRepository;

    public InteractionServiceImpl(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    @Override
    public Interaction saveInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }

    @Override
    public Interaction findInteractionById(Long id) {
        // return interactionRepository.findById(id).get();
        // return interactionRepository.findById(id).orElseGet(() -> new Interaction());
        return interactionRepository.findById(id).orElseThrow(() -> new InteractionNotFoundException(id));
    }

    @Override
    public List<Interaction> findAllInteraction() {
        return interactionRepository.findAll();
    }

    @Override
    public Interaction updateInteractionById(Long id, Interaction interaction) {
        // Interaction interactionToUpdate = interactionRepository.findById(id).get();
        // Interaction interactionToUpdate = interactionRepository.findById(id).orElseGet(() -> new Interaction());
        Interaction interactionToUpdate = interactionRepository.findById(id).orElseThrow(() -> new InteractionNotFoundException(id));
        interactionToUpdate.setRemarks(interaction.getRemarks());
        interactionToUpdate.setInteractionDate(interaction.getInteractionDate());
        return interactionRepository.save(interactionToUpdate);
    }

    @Override
    public void deleteInteractionById(Long id) {
        interactionRepository.deleteById(id);
    }
}
