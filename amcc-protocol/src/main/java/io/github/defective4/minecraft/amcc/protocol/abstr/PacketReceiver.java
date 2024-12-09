package io.github.defective4.minecraft.amcc.protocol.abstr;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import io.github.defective4.minecraft.amcc.protocol.MinecraftClient;
import io.github.defective4.minecraft.amcc.protocol.packets.ClientboundPacket;

public abstract class PacketReceiver {

    public void receivePacket(ClientboundPacket packet, MinecraftClient client) {
        for (Method method : getClass().getMethods()) if (method.isAnnotationPresent(PacketHandler.class)) {
            Parameter[] params = method.getParameters();
            if (params.length == 2 && params[0].getType() == packet.getClass()
                    && params[1].getType() == MinecraftClient.class) {
                try {
                    method.invoke(this, packet, client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
