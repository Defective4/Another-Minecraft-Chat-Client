package io.github.defective4.minecraft.amcc.protocol.v767.data;

public class DataPack {
    private final String namespace, id, version;

    public DataPack(String namespace, String id, String version) {
        this.namespace = namespace;
        this.id = id;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getVersion() {
        return version;
    }
}
