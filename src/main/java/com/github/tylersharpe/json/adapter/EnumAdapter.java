package com.github.tylersharpe.json.adapter;

import com.github.tylersharpe.json.JsonReader;
import com.github.tylersharpe.json.JsonWriter;
import com.github.tylersharpe.json.util.JavaType;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class EnumAdapter implements JsonAdapter<Enum> {

    private static final JsonAdapter<Enum> INSTANCE = new EnumAdapter().nullSafe();

    private EnumAdapter() {
    }

    public static JsonAdapter<Enum> getInstance() {
        return INSTANCE;
    }

    @Override
    public void writeObject(JsonWriter jsonWriter, Enum enumMember) throws IOException {
        jsonWriter.writeString(enumMember.name());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enum readObject(JsonReader reader, JavaType<? extends Enum> enumType) throws IOException {
        String enumValueName = reader.readString();
        try {
            return Enum.valueOf(enumType.getRawType(), enumValueName);
        } catch (IllegalArgumentException noEnum) {
            throw reader.createJsonBindException("Cannot parse '" + enumValueName + "' as " + enumType.getRawType(), noEnum);
        }
    }

}
