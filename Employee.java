package ru.university.lab2;

public class Employee {

  private final String name;
  private final Department department;

  public Employee(String name, Department department) {
    if (department == null) {
      throw new IllegalArgumentException("Отдел не может быть пустым");
    }
    this.name = formatEmployeeName(name);
    this.department = department;
    department.addEmployee(this);
  }

  private String formatEmployeeName(String name) {
    if (name == null) {
      return "Неизвестный";
    }
    String trimmed = name.trim();
    if (trimmed.isEmpty()) {
      return "Неизвестный";
    }
    return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
  }

  public String getName() {
    return name;
  }

  public Department getDepartment() {
    return department;
  }

  @Override
  public String toString() {
    Employee boss = department.getBoss();
    if (boss == null) {
      return name + " работает в отделе " + department.getName() + ", начальник не назначен";
    }
    if (this == boss) {
      return name + " начальник отдела " + department.getName();
    }
    return name + " работает в отделе " + department.getName() +
        ", начальник которого " + boss.getName();
  }
}

