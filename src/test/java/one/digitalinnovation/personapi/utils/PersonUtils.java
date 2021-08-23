package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {
    private static final String FIRST_NAME = "Cícero";
    private static final String LAST_NAME = "Silvarus";
    private static final String CPF = "360.721.396-80";
    private static final long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(2021, 1, 1);

    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate("01-01-2021")
                .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }

}
