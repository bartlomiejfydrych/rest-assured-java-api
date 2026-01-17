package utils.response;

import com.fasterxml.jackson.core.type.TypeReference;
import exceptions.ExceptionJsonDeserialization;
import io.restassured.response.Response;

import java.util.List;

import static providers.ProviderObjectMapper.getObjectMapper;
import static utils.response.UtilsResponseValidator.validateDto;
import static utils.response.UtilsResponseValidator.validateDtoList;

public final class UtilsResponseDeserializer {

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private UtilsResponseDeserializer() {
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // ----------------------
    // OBJECT DESERIALIZATION
    // ----------------------

    // EXAMPLE OF USE:
    // UserDto user = deserializeAndValidateJson(response, UserDto.class);

    // DESERIALIZE + VALIDATE

    public static <T> T deserializeAndValidateJson(Response response, Class<T> clazz) {
        return deserializeAndValidateJson(response.asString(), clazz);
    }

    public static <T> T deserializeAndValidateJson(String json, Class<T> clazz) {
        T dto = deserializeJson(json, clazz);
        validateDto(dto);
        return dto;
    }

    // DESERIALIZE

    public static <T> T deserializeJson(Response response, Class<T> clazz) {
        return deserializeJson(response.asString(), clazz);
    }

    public static <T> T deserializeJson(String json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            throw new ExceptionJsonDeserialization("Failed to deserialize JSON into " + clazz.getSimpleName(), e);
        }
    }

    // --------------------
    // LIST DESERIALIZATION
    // --------------------

    // EXAMPLE OF USE:
    // List<UserDto> users = deserializeAndValidateJson(response, new TypeReference<List<UserDto>>() {});

    // DESERIALIZE + VALIDATE

    public static <T> List<T> deserializeAndValidateJson(Response response, TypeReference<List<T>> typeRef) {
        return deserializeAndValidateJson(response.asString(), typeRef);
    }

    public static <T> List<T> deserializeAndValidateJson(String json, TypeReference<List<T>> typeRef) {
        List<T> list = deserializeJson(json, typeRef);
        validateDtoList(list);
        return list;
    }

    // DESERIALIZE

    public static <T> List<T> deserializeJson(Response response, TypeReference<List<T>> typeRef) {
        return deserializeJson(response.asString(), typeRef);
    }

    public static <T> List<T> deserializeJson(String json, TypeReference<List<T>> typeRef) {
        try {
            return getObjectMapper().readValue(json, typeRef);
        } catch (Exception e) {
            throw new ExceptionJsonDeserialization("Failed to deserialize JSON into type " + typeRef.getType(), e);
        }
    }
}
