package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.PhoneDTO;
import one.digitalinnovation.personapi.entity.Phone;
import one.digitalinnovation.personapi.enums.PhoneType;

public class PhoneUtils {
    private static final String PHONE_NUMBER = "119999999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final Long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

}
