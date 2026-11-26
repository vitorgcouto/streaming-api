package vitorgcouto.streaming.api.domain;

public enum Perfil {
    INFANTIL, ADOLESCENTE, ADULTO;


    public boolean podeAssistir(Perfil exigido){
        return this.compareTo(exigido) >= 0;
    }
}
