package ru.university.lab2;

public class Time {

  private final int seconds;

  public Time(int seconds) {
    this.seconds = Math.floorMod(seconds, 24 * 3600);
  }

  private int getHours() {
    return seconds / 3600;
  }

  private int getMinutes() {
    return (seconds % 3600) / 60;
  }

  private int getSeconds() {
    return seconds % 60;
  }

  @Override
  public String toString() {
    return String.format("%d:%02d:%02d", getHours(), getMinutes(), getSeconds());
  }
}
