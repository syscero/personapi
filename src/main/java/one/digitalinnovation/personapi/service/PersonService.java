package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

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

        return createMethodResponse(saved.getId(), "Created person with ID ");
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
        Person item = findByIdOrThrow(id);

        return personMapper.toDTO(item);
    }

    public void delete(Long id) throws PersonNotFoundException {
        findByIdOrThrow(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO dto) throws PersonNotFoundException {
        Person item = findByIdOrThrow(id);

        dto.setId(item.getId());
        Person personToSave = personMapper.toModel(dto);
        Person saved = personRepository.save(personToSave);

        return createMethodResponse(saved.getId(), "Updated person with ID ");
    }

    private MessageResponseDTO createMethodResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person findByIdOrThrow(Long id) throws PersonNotFoundException {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
