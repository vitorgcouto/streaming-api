package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class SenhaTest {

    @Test
    void senhaCriptografadaNaoDeveSerIgualASenhaOriginal() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = encoder.encode("minhaSenha123");

        assertNotEquals("minhaSenha123", hash);
        assertTrue(encoder.matches("minhaSenha123", hash));
        assertFalse(encoder.matches("123", hash));
    }
}
