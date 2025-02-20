package origami_flow.salgado_trancas_api.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class HashMapConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertMapToObject(Map<String, Object> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
