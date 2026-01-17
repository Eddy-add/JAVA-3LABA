package ru.university.lab2;

public class House {

  private int floors;

  public House(int floors) {
    this.floors = Math.max(0, floors);
  }


  public int getFloors() {
    return floors;
  }

  public void setFloors(int floors) {
    this.floors = Math.max(0, floors);
  }

  private String getFloorWord() {
    int n = Math.abs(floors);
    int lastTwo = n % 100;
    int last = n % 10;

    if (floors % 10 == 1 && floors % 100 != 11) {
      return "этажом";
    } else if ((floors % 10 >= 2 && floors % 10 <= 4) && (floors % 100 < 10
        || floors % 100 >= 20)) {
      return "этажами";
    } else {
      return "этажами";
    }
  }

  @Override
  public String toString() {
    return "дом с " + floors + " " + getFloorWord();
  }
}
