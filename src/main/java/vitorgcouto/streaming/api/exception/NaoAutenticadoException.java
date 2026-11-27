package vitorgcouto.streaming.api.exception;

public class NaoAutenticadoException extends RuntimeException {
    public NaoAutenticadoException() {
        super("Faça login para continuar");
    }
}
