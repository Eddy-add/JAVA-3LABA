package ru.university.lab2;

public class Gun {

  private int bullets;

  public Gun(int bullets) {
    this.bullets = Math.max(0, bullets);
  }

  public Gun() {
    this(5);
  }

  public int getBullets() {
    return bullets;
  }

  public void shoot() {
    if (bullets > 0) {
      System.out.println("Бах!");
      bullets--;
    } else {
      System.out.println("Клац!");
    }
  }

  @Override
  public String toString() {
    return "Пистолет с " + bullets + " патронами";
  }
}
