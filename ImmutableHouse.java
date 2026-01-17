package ru.university.lab2;

public class ImmutableHouse {

  private final int floors;

  public ImmutableHouse(int floors) {
    this.floors = Math.max(0, floors);
  }

  public int getFloors() {
    return floors;
  }

  private String getFloorWord() {
    if (floors % 10 == 1 && floors % 100 != 11) {
      return "этажом";
    } else {
      return "этажами";
    }
  }

  @Override
  public String toString() {
    return "неизменяемый дом с " + floors + " " + getFloorWord();
  }
}
