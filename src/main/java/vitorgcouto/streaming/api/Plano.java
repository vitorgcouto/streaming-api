package vitorgcouto.streaming.api;

public enum Plano {
    BASICO(2), PREMIUM(4);

    private final int limiteUsuarios;

    Plano(int limiteUsuarios) {
        this.limiteUsuarios = limiteUsuarios;
    }

    public int getLimiteUsuarios() {
        return limiteUsuarios;
    }
}
