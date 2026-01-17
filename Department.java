package ru.university.lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Department {

  private final String name;
  private Employee boss;
  private final List<Employee> employees;

  public Department(String name) {
    this.name = formatDepartmentName(name);
    this.employees = new ArrayList<>();
  }

  private String formatDepartmentName(String name) {
    if (name == null || name.trim().isEmpty()) {
      return "Без названия";
    }
    return name.trim().toUpperCase();
  }

  public String getName() {
    return name;
  }

  public Employee getBoss() {
    return boss;
  }

  public void setBoss(Employee boss) {
    if (boss == null) {
      throw new IllegalArgumentException("Начальник не может быть null");
    }
    if (boss.getDepartment() != this) {
      throw new IllegalArgumentException("Начальник должен быть сотрудником этого отдела");
    }
    this.boss = boss;
  }

  public void addEmployee(Employee employee) {
    if (employee == null) {
      return;
    }
    if (!employees.contains(employee)) {
      employees.add(employee);
    }
  }

  public List<Employee> getEmployees() {
    return Collections.unmodifiableList(employees);
  }
}
