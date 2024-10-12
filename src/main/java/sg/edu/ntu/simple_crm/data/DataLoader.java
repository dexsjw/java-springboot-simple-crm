package sg.edu.ntu.simple_crm.data;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.edu.ntu.simple_crm.repositories.CustomerRepository;
import sg.edu.ntu.simple_crm.repositories.InteractionRepository;

@Component
public class DataLoader {

    private CustomerRepository customerRepository;
    private InteractionRepository interactionRepository;
    
    public DataLoader(CustomerRepository customerRepository, InteractionRepository interactionRepository) {
        this.customerRepository = customerRepository;
        this.interactionRepository = interactionRepository;
    }

    @PostConstruct
    public void loadData() {
        customerRepository.deleteAll();
        interactionRepository.deleteAll();

        // Load data
        Customer bruceBanner = new Customer("Bruce", "Banner", 1984);
        customerRepository.save(bruceBanner);
        customerRepository.save(new Customer("Peter", "Parker", 2005));
        customerRepository.save(new Customer("Stephen", "Strange", 1983));
        customerRepository.save(new Customer("Steve", "Rogers", 1940));
        customerRepository.save(new Customer("Tony", "Stark", 1990));

        Interaction initialInteraction = new Interaction();
        initialInteraction.setRemarks("Test interaction remark");
        initialInteraction.setInteractionDate(LocalDate.now());
        initialInteraction.setCustomer(bruceBanner);
        interactionRepository.save(initialInteraction);
    }

}
