package com.minecraft.moonlake.api.nms.packet;

import com.minecraft.moonlake.property.ObjectProperty;
import com.minecraft.moonlake.property.SimpleObjectProperty;
import com.minecraft.moonlake.reflect.Reflect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by MoonLake on 2016/7/21.
 */
public class PacketPlayOutEntityDestroy extends PacketAbstract<PacketPlayOutEntityDestroy> {

    private ObjectProperty<int[]> entityIds;

    public PacketPlayOutEntityDestroy(int... entityIds) {

        this.entityIds = new SimpleObjectProperty<>(entityIds);
    }

    public PacketPlayOutEntityDestroy(Entity... entities) {

        this.entityIds = new SimpleObjectProperty<>(new int[entities.length]);

        int index = 0;

        for(Entity entity : entities) {

            this.entityIds.get()[index] = entity.getEntityId();
            index++;
        }
    }

    public ObjectProperty<int[]> getEntityIds() {

        return entityIds;
    }

    /**
     * 将此数据包发送给指定玩家
     *
     * @param names 玩家名
     */
    @Override
    public void send(String... names) {

        try {

            Class<?> PacketPlayOutEntityDestroy = Reflect.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutEntityDestroy");

            Object ppoed = Reflect.instantiateObject(PacketPlayOutEntityDestroy, entityIds.get());

            Class<?> Packet = Reflect.PackageType.MINECRAFT_SERVER.getClass("Packet");
            Class<?> CraftPlayer = Reflect.PackageType.CRAFTBUKKIT_ENTITY.getClass("CraftPlayer");
            Class<?> EntityPlayer = Reflect.PackageType.MINECRAFT_SERVER.getClass("EntityPlayer");
            Class<?> PlayerConnection = Reflect.PackageType.MINECRAFT_SERVER.getClass("PlayerConnection");

            Method getHandle = Reflect.getMethod(CraftPlayer, "getHandle");
            Player[] players = PacketManager.getPlayersfromNames(names);

            Method sendPacket = Reflect.getMethod(PlayerConnection, "sendPacket", Packet);

            for(Player player : players) {

                Object NMSPlayer = getHandle.invoke(player);
                Field playerConnection = Reflect.getField(EntityPlayer, true, "playerConnection");

                sendPacket.invoke(playerConnection.get(NMSPlayer), ppoed);
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }
}