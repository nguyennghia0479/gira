package cybersoft.javabackend.java18.gira.common.util;

import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import cybersoft.javabackend.java18.gira.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GiraMapperTest {
    GiraMapper mapper = new GiraMapper();

    @Test
    void testUserMapper() {
        UserDTO userDTO = UserDTO.builder()
                .username("ccc")
                .password("123")
                .email("ccc@gmail.com")
                .build();
        User user = mapper.map(userDTO, User.class);
        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
    }
}