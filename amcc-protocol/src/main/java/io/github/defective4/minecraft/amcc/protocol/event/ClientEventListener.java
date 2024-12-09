package io.github.defective4.minecraft.amcc.protocol.event;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public interface ClientEventListener {
    default void dispatchEvent(ClientEvent event) {
        for (Method method : getClass().getMethods()) if (method.isAnnotationPresent(EventHandler.class)) {
            Parameter[] params = method.getParameters();
            if (params.length == 1 && params[0].getType() == event.getClass()) try {
                method.invoke(this, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
