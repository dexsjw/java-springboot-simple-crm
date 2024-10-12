package sg.edu.ntu.simple_crm.services;

import java.util.List;

import sg.edu.ntu.simple_crm.data.Interaction;

public interface InteractionService {
    Interaction saveInteraction(Interaction interaction);

    Interaction findInteractionById(Long id);

    List<Interaction> findAllInteraction();

    Interaction updateInteractionById(Long id, Interaction interaction);

    void deleteInteractionById(Long id);
}
