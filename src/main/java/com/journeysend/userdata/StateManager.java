package com.journeysend.userdata;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.PersistentStateManager;

public class StateManager {
  public static <T extends DataState> T getOrCreate(ServerPlayerEntity player, Class<T> clazz, String prefix) {
    String key = prefix + player.getUuidAsString();
    PersistentStateManager manager = player.getServerWorld().getPersistentStateManager();

    return manager.getOrCreate(
      (nbt) -> fromNbt(clazz, nbt),
      () -> newInstance(clazz),
      key
    );
  }

  public static <T extends DataState> T newInstance(Class<T> clazz) {
    try {
      return clazz.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      Userdata.LOGGER.error("Failed to create new instance of {}", clazz.getName(), e);
      throw new RuntimeException(e);
    }
  }

  public static <T extends DataState> T fromNbt(Class<T> clazz, NbtCompound nbt) {
    try {
      return (T) clazz.getDeclaredMethod("fromNbt", NbtCompound.class)
        .invoke(null, nbt);
    } catch (Exception e) {
      Userdata.LOGGER.error("Failed to deserialize state for {}", clazz.getName(), e);
      return newInstance(clazz);
    }
  }

  public static <T extends DataState> void save(ServerPlayerEntity player, Class<T> clazz, String prefix) {
    getOrCreate(player, clazz, prefix).markDirty();
  }
}
