package vitorgcouto.streaming.api;

public enum Perfil {
    INFANTIL, ADOLESCENTE, ADULTO;


    public boolean podeAssistir(Perfil exigido){
        return this.compareTo(exigido) >= 0;
    }
}
