package com.journeysend.userdata.custom;

import com.journeysend.userdata.DataState;
import net.minecraft.nbt.NbtCompound;

public class UserData extends DataState {
  public String charPronouns;
  public String userPronouns;
  public String preferredName;
  public String charAge;

  public UserData() {
    this.charPronouns = "";
    this.userPronouns = "";
    this.preferredName = "";
    this.charAge = "";
  }

  public UserData(String charPronouns, String userPronouns, String preferred, String age) {
    this.charPronouns = charPronouns;
    this.userPronouns = userPronouns;
    this.preferredName = preferred;
    this.charAge = age;
  }

  @Override
  public NbtCompound writeNbt(NbtCompound nbt) {
    nbt.putString("charPronouns", charPronouns);
    nbt.putString("userPronouns", userPronouns);
    nbt.putString("preferredName", preferredName);
    nbt.putString("charAge", charAge);
    return nbt;
  }

  public static UserData fromNbt(NbtCompound nbt) {
    return new UserData(nbt.getString("charPronouns"), nbt.getString("userPronouns"), nbt.getString("preferredName"), nbt.getString("charAge"));
  }
}
