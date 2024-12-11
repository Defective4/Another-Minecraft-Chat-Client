package io.github.defective4.minecraft.amcc.protocol.v767;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.defective4.minecraft.amcc.protocol.abstr.Codec;
import io.github.defective4.minecraft.amcc.protocol.data.BlockPosition;
import io.github.defective4.minecraft.amcc.protocol.data.PlayerInfoAction;

public class V767Codecs {

    public static final Codec<BlockPosition> BLOCK_POSITION = new Codec<BlockPosition>() {

        @Override
        public BlockPosition read(DataInput in) throws IOException {
            in.readLong();
            return new BlockPosition(0, 0, 0); // TODO
        }

        @Override
        public void write(DataOutput out, BlockPosition val) throws IOException {
            out.writeLong(0); // TODO
        }
    };

    public static final Codec<List<PlayerInfoAction>> PLAYER_INFO_ACTIONS = new Codec<List<PlayerInfoAction>>() {

        @Override
        public List<PlayerInfoAction> read(DataInput in) throws IOException {
            List<PlayerInfoAction> actions = new ArrayList<>();
            byte mask = in.readByte();
            for (Map.Entry<PlayerInfoAction, Integer> entry : INFO_ACTIONS.entrySet()) {
                if ((mask & entry.getValue()) != 0) actions.add(entry.getKey());
            }
            return Collections.unmodifiableList(actions);
        }

        @Override
        public void write(DataOutput out, List<PlayerInfoAction> val) throws IOException {
            int mask = 0;
            for (PlayerInfoAction action : val) mask |= INFO_ACTIONS.getOrDefault(action, 0);
            out.writeByte(mask);
        }
    };

    private static final Map<PlayerInfoAction, Integer> INFO_ACTIONS = new HashMap<>();

    static {
        INFO_ACTIONS.put(PlayerInfoAction.ADD_PLAYER, 0x01);
        INFO_ACTIONS.put(PlayerInfoAction.INITIALIZE_CHAT, 0x02);
        INFO_ACTIONS.put(PlayerInfoAction.UPDATE_GAMEMODE, 0x04);
        INFO_ACTIONS.put(PlayerInfoAction.UPDATE_LISTED, 0x08);
        INFO_ACTIONS.put(PlayerInfoAction.UPDATE_LATENCY, 0x10);
        INFO_ACTIONS.put(PlayerInfoAction.UPDATE_DISPLAY_NAME, 0x20);
    }
}
