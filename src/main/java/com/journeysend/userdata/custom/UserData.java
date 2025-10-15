package com.journeysend.userdata.custom;

import com.journeysend.userdata.DataState;
import net.minecraft.nbt.NbtCompound;

public class UserData extends DataState {
  public String charPronouns;
  public String preferred;
  public String age;

  public UserData() {
    this.charPronouns = "";
    this.preferred = "";
    this.age = "";
  }

  public UserData(String charPronouns, String preferred, String age) {
    this.charPronouns = charPronouns;
    this.preferred = preferred;
    this.age = age;
  }

  @Override
  public NbtCompound writeNbt(NbtCompound nbt) {
    nbt.putString("charPronouns", charPronouns);
    nbt.putString("preferred", preferred);
    nbt.putString("age", age);
    return nbt;
  }

  public static UserData fromNbt(NbtCompound nbt) {
    return new UserData(nbt.getString("charPronouns"), nbt.getString("preferred"), nbt.getString("age"));
  }
}
