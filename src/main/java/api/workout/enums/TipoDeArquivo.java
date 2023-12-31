package api.workout.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TipoDeArquivo {

    XLSX(1,"xlsx"),
    TXT(2,"txt"),
    PDF(3,"pdf");

    private final Integer id;
    private final String description;

    TipoDeArquivo(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static TipoDeArquivo findById(Integer id){
        return Stream.of(TipoDeArquivo.values())
                .filter(value -> value.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", id)));
    }
    public static TipoDeArquivo findByDescription(String description){
        return Stream.of(TipoDeArquivo.values())
                .filter(value -> value.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("File %s not found", description)));
    }
}
