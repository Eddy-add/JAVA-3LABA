package ru.university.lab2;

import java.util.Scanner;

public class Main {

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println(
        "=== ЛАБОРАТОРНАЯ РАБОТА №2 - ОБЪЕКТНО-ОРИЕНТИРОВАННОЕ ПРОГРАММИРОВАНИЕ ===\n");

    while (true) {
      showMainMenu();
      int choice = readIntInRange("Выберите задание (1-7): ", 1, 7);

      switch (choice) {
        case 1 -> demoTime();
        case 2 -> demoHouse();
        case 3 -> demoEmployees();
        case 4 -> demoExtendedEmployees();
        case 5 -> demoImmutableHouse();
        case 6 -> demoGun();
        case 7 -> {
          System.out.println("Выход из программы...");
          scanner.close();
          return;
        }
      }

      pressEnterToContinue();
    }
  }

  private static void showMainMenu() {
    System.out.println("ВЫБОР ЗАДАНИЙ:");
    System.out.println("1. Задание 1.4 - Время");
    System.out.println("2. Задание 1.5 - Дом");
    System.out.println("3. Задание 2.4 - Сотрудники и отделы");
    System.out.println("4. Задание 3.4 - Расширенные сотрудники");
    System.out.println("5. Задание 4.3 - Неизменяемый дом");
    System.out.println("6. Задание 5.1 - Пистолет");
    System.out.println("7. Выход");
  }

  private static void pressEnterToContinue() {
    System.out.println("\nНажмите Enter чтобы продолжить...");
    scanner.nextLine();
  }

  private static void demoTime() {
    System.out.println("\n=== ЗАДАНИЕ 1.4 - ВРЕМЯ ===");

    System.out.println("Стандартные примеры:");
    Time time1 = new Time(10);
    Time time2 = new Time(10000);
    Time time3 = new Time(100000);

    System.out.println("10 секунд: " + time1);
    System.out.println("10000 секунд: " + time2);
    System.out.println("100000 секунд: " + time3);

    System.out.println("\n--- ВВОД ПОЛЬЗОВАТЕЛЯ ---");
    int seconds = readIntMin("Введите количество секунд: ", 0);
    Time userTime = new Time(seconds);
    System.out.println("Ваше время: " + userTime);
  }

  private static void demoHouse() {
    System.out.println("\n=== ЗАДАНИЕ 1.5 - ДОМ ===");

    System.out.println("Стандартные примеры:");
    House house1 = new House(1);
    House house2 = new House(5);
    House house3 = new House(23);

    System.out.println(house1);
    System.out.println(house2);
    System.out.println(house3);

    System.out.println("\n--- ВВОД ПОЛЬЗОВАТЕЛЯ ---");
    int floors = readIntMin("Введите количество этажей для дома: ", 0);
    House userHouse = new House(floors);
    System.out.println("Созданный дом: " + userHouse);

    String answer = readLine("\nХотите изменить количество этажей? (да/нет): ");
    if (answer.equalsIgnoreCase("да")) {
      int newFloors = readIntMin("Введите новое количество этажей: ", 0);
      userHouse.setFloors(newFloors);
      System.out.println("Обновленный дом: " + userHouse);
    }
  }

  private static void demoEmployees() {
    System.out.println("\n=== ЗАДАНИЕ 2.4 - СОТРУДНИКИ И ОТДЕЛЫ ===");

    String deptName = readValidatedName("Введите название отдела: ");
    Department department = new Department(deptName);

    int empCount = readIntMin("Введите количество сотрудников в отделе: ", 0);

    for (int i = 0; i < empCount; i++) {
      String empName = readValidatedName("Введите имя сотрудника " + (i + 1) + ": ");
      new Employee(empName, department);
    }

    if (department.getEmployees().isEmpty()) {
      System.out.println("Сотрудников нет — начальника назначить нельзя.");
      return;
    }

    while (true) {
      System.out.println("\nДоступные сотрудники:");
      for (Employee emp : department.getEmployees()) {
        System.out.println("  - " + emp.getName());
      }

      String bossName = readValidatedName("Введите имя начальника отдела: ");
      Employee boss = findEmployeeByName(department, bossName);

      if (boss != null) {
        department.setBoss(boss);
        System.out.println("Начальник назначен: " + boss.getName());
        break;
      }

      System.out.println("Сотрудник с таким именем не найден!");
      String exitChoice = readLine("Хотите выйти из назначения начальника? (да/нет): ");
      if (exitChoice.equalsIgnoreCase("да")) {
        System.out.println("Начальник не назначен. Отдел будет без начальника.");
        break;
      }
    }

    System.out.println("\nИнформация о сотрудниках:");
    for (Employee emp : department.getEmployees()) {
      System.out.println(emp);
    }
  }

  private static void demoExtendedEmployees() {
    System.out.println("\n=== ЗАДАНИЕ 3.4 - РАСШИРЕННЫЕ СОТРУДНИКИ ===");

    Department itDepartment = new Department("IT");

    System.out.println("Введите сотрудников отдела IT.");
    System.out.println("Чтобы закончить ввод — напишите: стоп");

    while (true) {
      String name = readLine("Имя сотрудника: ");
      if (name.equalsIgnoreCase("стоп")) {
        break;
      }

      // если хочешь строгую проверку букв — можно так:
      if (!name.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
        System.out.println("Ошибка: имя должно содержать только буквы!");
        continue;
      }

      // форматирование как в readValidatedName
      name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

      new Employee(name, itDepartment);
      System.out.println("Добавлен: " + name);
    }

    if (itDepartment.getEmployees().isEmpty()) {
      System.out.println("Ни одного сотрудника не добавлено. Демонстрация завершена.");
      return;
    }

    System.out.println("\nСписок сотрудников отдела IT:");
    for (Employee employee : itDepartment.getEmployees()) {
      System.out.println("  - " + employee.getName());
    }

    String bossAnswer = readLine("\nХотите назначить начальника? (да/нет): ");
    if (bossAnswer.equalsIgnoreCase("да")) {
      while (true) {
        String bossName = readLine("Введите имя начальника (как в списке): ");

        Employee boss = findEmployeeByName(itDepartment, bossName);
        if (boss != null) {
          itDepartment.setBoss(boss);
          System.out.println("Начальник отдела назначен: " + boss.getName());
          break;
        }

        String retry = readLine("Такого сотрудника нет. Повторить? (да/нет): ");
        if (!retry.equalsIgnoreCase("да")) {
          System.out.println("Начальник не назначен.");
          break;
        }
      }
    }

    if (itDepartment.getBoss() != null) {
      System.out.println("\nНачальник отдела: " + itDepartment.getBoss().getName());
    } else {
      System.out.println("\nНачальник отдела не назначен.");
    }
  }


  private static void demoImmutableHouse() {
    System.out.println("\n=== ЗАДАНИЕ 4.3 - НЕИЗМЕНЯЕМЫЙ ДОМ ===");

    System.out.println("Стандартные примеры:");
    ImmutableHouse immutableHouse1 = new ImmutableHouse(2);
    ImmutableHouse immutableHouse2 = new ImmutableHouse(35);
    ImmutableHouse immutableHouse3 = new ImmutableHouse(91);

    System.out.println(immutableHouse1);
    System.out.println(immutableHouse2);
    System.out.println(immutableHouse3);

    System.out.println("\n--- ВВОД ПОЛЬЗОВАТЕЛЯ ---");
    int floors = readIntMin("Введите количество этажей для неизменяемого дома: ", 0);
    ImmutableHouse userImmutableHouse = new ImmutableHouse(floors);
    System.out.println("Созданный неизменяемый дом: " + userImmutableHouse);

    System.out.println("\nДемонстрация неизменяемости:");
    ImmutableHouse newHouse = new ImmutableHouse(floors + 10);
    System.out.println("Новый дом: " + newHouse);
    System.out.println("Старый дом остался неизменным: " + userImmutableHouse);
  }

  private static void demoGun() {
    System.out.println("\n=== ЗАДАНИЕ 5.1 - ПИСТОЛЕТ ===");

    System.out.println("Выберите тип создания пистолета:");
    System.out.println("1 - С указанием количества патронов");
    System.out.println("2 - С патронами по умолчанию (5)");

    int choice = readIntInRange("Ваш выбор (1-2): ", 1, 2);

    Gun gun;
    if (choice == 1) {
      int bullets = readIntMin("Введите количество патронов: ", 0);
      gun = new Gun(bullets);
    } else {
      gun = new Gun();
    }

    System.out.println("Создан: " + gun);

    int shots = readIntMin("\nСколько раз вы хотите выстрелить? ", 0);

    System.out.println("Стреляем " + shots + " раз:");
    for (int i = 0; i < shots; i++) {
      System.out.print("Выстрел " + (i + 1) + ": ");
      gun.shoot();
    }

    System.out.println("После стрельбы: " + gun);
  }

  // =========================
  // INPUT HELPERS
  // =========================

  private static String readLine(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
  }

  private static int readIntMin(String prompt, int min) {
    while (true) {
      String s = readLine(prompt);
      try {
        int v = Integer.parseInt(s);
        if (v >= min) {
          return v;
        }
        System.out.println("Ошибка: число должно быть >= " + min);
      } catch (NumberFormatException e) {
        System.out.println("Ошибка: введите целое число!");
      }
    }
  }

  private static int readIntInRange(String prompt, int min, int max) {
    while (true) {
      String s = readLine(prompt);
      try {
        int v = Integer.parseInt(s);
        if (v >= min && v <= max) {
          return v;
        }
        System.out.printf("Ошибка: число должно быть от %d до %d!\n", min, max);
      } catch (NumberFormatException e) {
        System.out.println("Ошибка: введите целое число!");
      }
    }
  }

  private static String readValidatedName(String prompt) {
    while (true) {
      String input = readLine(prompt);
      if (input.isEmpty()) {
        System.out.println("Ошибка: значение не может быть пустым!");
        continue;
      }
      if (!input.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
        System.out.println("Ошибка: можно вводить только буквы!");
        continue;
      }
      return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
  }

  private static Employee findEmployeeByName(Department department, String name) {
    if (department == null || name == null) {
      return null;
    }
    for (Employee emp : department.getEmployees()) {
      if (emp.getName().equalsIgnoreCase(name)) {
        return emp;
      }
    }
    return null;
  }
}
