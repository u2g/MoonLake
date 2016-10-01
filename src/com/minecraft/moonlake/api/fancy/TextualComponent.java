package com.minecraft.moonlake.api.fancy;

import com.google.common.collect.ImmutableMap;
import com.google.gson.stream.JsonWriter;
import com.minecraft.moonlake.property.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by MoonLake on 2016/9/16.
 */
public abstract class TextualComponent implements Cloneable {

    public abstract ReadOnlyStringProperty getKey();

    public abstract String getReadableString();

    @Override
    public abstract TextualComponent clone() throws CloneNotSupportedException;

    public abstract void writeJson(JsonWriter jsonWriter) throws IOException;

    static boolean isTextKey(String key) {

        return key.equals("translate") || key.equals("text") || key.equals("score") || key.equals("selector");
    }

    static boolean isTranslatableText(TextualComponent component) {

        return component instanceof ComplexTextTypeComponent && ((ComplexTextTypeComponent) component).getKey().equals("translate");
    }

    public static TextualComponent rawText(String text) {

        return new ArbitraryTextTypeComponent("text", text);
    }

    public static TextualComponent localizedText(String translateKey) {

        return new ArbitraryTextTypeComponent("translate", translateKey);
    }

    public static TextualComponent objectiveScore(String scoreboardObjective) {

        return objectiveScore("*", scoreboardObjective);
    }

    public static TextualComponent objectiveScore(String playerName, String scoreboardObjective) {

        return new ComplexTextTypeComponent("score", ImmutableMap.<String, String>builder()
                .put("name", playerName)
                .put("objective", scoreboardObjective)
                .build());
    }

    public static TextualComponent selector(String selector) {

        return new ArbitraryTextTypeComponent("selector", selector);
    }

    private final static class ArbitraryTextTypeComponent extends TextualComponent {

        private StringProperty key;
        private StringProperty value;

        public ArbitraryTextTypeComponent(String key, String value) {

            this.key = new SimpleStringProperty(key);
            this.value = new SimpleStringProperty(value);
        }

        @Override
        public StringProperty getKey() {

            return key;
        }

        public StringProperty getValue() {

            return value;
        }

        @Override
        public String getReadableString() {

            return getValue().get();
        }

        @Override
        public TextualComponent clone() throws CloneNotSupportedException {

            return new ArbitraryTextTypeComponent(getKey().get(), getValue().get());
        }

        @Override
        public void writeJson(JsonWriter jsonWriter) throws IOException {

            jsonWriter.name(getKey().get()).value(getValue().get());
        }
    }

    private final static class ComplexTextTypeComponent extends TextualComponent {

        private StringProperty key;
        private ObjectProperty<Map<String, String>> values;

        public ComplexTextTypeComponent(String key, Map<String, String> values) {

            this.key = new SimpleStringProperty(key);
            this.values = new SimpleObjectProperty<>(values);
        }

        @Override
        public StringProperty getKey() {

            return key;
        }

        public ObjectProperty<Map<String, String>> getValues() {

            return values;
        }

        @Override
        public String getReadableString() {

            return getKey().get();
        }

        @Override
        public TextualComponent clone() throws CloneNotSupportedException {

            return new ComplexTextTypeComponent(getKey().get(), getValues().get());
        }

        @Override
        public void writeJson(JsonWriter jsonWriter) throws IOException {

            jsonWriter.name(getKey().get());
            jsonWriter.beginObject();

            for(Map.Entry<String, String> entry : getValues().get().entrySet()) {

                jsonWriter.name(entry.getKey()).value(entry.getValue());
            }
            jsonWriter.endObject();
        }
    }
}
