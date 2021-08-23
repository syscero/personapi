package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO dto = createFakeDTO();
        Person expectedEntity = createFakeEntity();
        MessageResponseDTO expectedRet = MessageResponseDTO
                .builder()
                .message("Created person with ID " + expectedEntity.getId())
                .build();

        Mockito.when(personRepository.save(expectedEntity)).thenReturn(expectedEntity);

        MessageResponseDTO ret = personService.createPerson(dto);

        assertEquals(expectedRet.getMessage(), ret.getMessage());
    }

    @Test
    void listAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void updateById() {
    }
}