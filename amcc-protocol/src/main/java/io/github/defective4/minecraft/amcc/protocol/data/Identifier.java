package io.github.defective4.minecraft.amcc.protocol.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

import io.github.defective4.minecraft.amcc.protocol.abstr.Codec;

public class Identifier {

    public static final Codec<Identifier> CODEC = new Codec<Identifier>() {

        @Override
        public Identifier read(DataInput in) throws IOException {
            return Identifier.fromString(DataTypes.readVarString(in));
        }

        @Override
        public void write(DataOutput out, Identifier val) throws IOException {
            DataTypes.writeVarString(out, val.toString());
        }
    };

    private static final String NS_PATTERN = "[a-z0-9.\\-_]*";
    private static final String VAL_PATTERN = "[a-z0-9.\\-_/]*";

    private final String namespace;
    private final String value;

    private Identifier(String namespace, String value) {
        if (!namespace.matches(NS_PATTERN))
            throw new IllegalArgumentException("Invalid namespace " + namespace + " for pattern " + NS_PATTERN);
        if (!value.matches(VAL_PATTERN))
            throw new IllegalArgumentException("Invalid value " + value + " for pattern " + VAL_PATTERN);
        this.namespace = namespace;
        this.value = value;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return namespace + ":" + value;
    }

    public static Identifier fromString(String str) {
        Objects.requireNonNull(str);
        String ns = "minecraft";
        String val = str;
        int index = val.indexOf(':');
        if (index > 0) {
            ns = val.substring(0, index);
            val = val.substring(index + 1);
        }
        return new Identifier(ns, val);
    }

}
