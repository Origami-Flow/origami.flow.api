package origami_flow.salgado_trancas_api.constans;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoEventoEnum {
    PESSOAL("Pessoal"),
    ATENDIMENTO("Atendimento");

    private final String tipo;

    TipoEventoEnum(String tipo) {
        this.tipo = tipo;
    }

    @JsonCreator
    public static TipoEventoEnum fromValue(String value) {
        for (TipoEventoEnum tipo : TipoEventoEnum.values()) {
            if (tipo.tipo.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }


    public String getTipo() {
        return tipo;
    }
}
