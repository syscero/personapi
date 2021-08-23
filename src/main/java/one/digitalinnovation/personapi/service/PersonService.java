package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     *
     * @param dto
     * @return
     */
    public MessageResponseDTO createPerson(PersonDTO dto) {
//        Person personToSave = Person.builder()
//                .firstName(dto.getFirstName())
//                .lastName(dto.getLastName())
//                .birthDate(dto.getBirthDate())
//                .phones(dto.getPhones())
//                .build();

        Person personToSave = personMapper.toModel(dto);
        Person saved = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + saved.getId())
                .build();
    }

    /**
     *
     * @return
     */
    public List<PersonDTO> listAll() {
        List<Person> all = personRepository.findAll();

        return all.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     * @throws PersonNotFoundException
     */
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person item = personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(item);
    }
}
