package sg.edu.ntu.simple_crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.simple_crm.data.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    
}
