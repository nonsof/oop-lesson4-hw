//Сначала создадим класс Учитель (Teacher), который будет представлять учителей:

public class Учитель {
    private int id;
    private String имя;
    private String фамилия;

    public Учитель(int id, String имя, String фамилия) {
        this.id = id;
        this.имя = имя;
        this.фамилия = фамилия;
    }

    // Геттеры и сеттеры для полей id, имя и фамилия
}

//Теперь создадим класс УчительСервис (TeacherService), который будет отвечать за управление учителями:

import java.util.ArrayList;
import java.util.List;

public class УчительСервис {
    private List<Учитель> учителя = new ArrayList<>();
    private int текущийId = 1;

    public void создатьУчителя(String имя, String фамилия) {
        Учитель учитель = new Учитель(текущийId, имя, фамилия);
        учителя.add(учитель);
        текущийId++;
    }

    public void редактироватьУчителя(int id, String новоеИмя, String новаяФамилия) {
        for (Учитель учитель : учителя) {
            if (учитель.getId() == id) {
                учитель.setИмя(новоеИмя);
                учитель.setФамилия(новаяФамилия);
                break;
            }
        }
    }

    public List<Учитель> получитьСписокУчителей() {
        return учителя;
    }
}


//Теперь создадим класс УчительВью (TeacherView), который будет отвечать за отображение информации о учителях:

public class УчительВью {
    public void показатьСписокУчителей(List<Учитель> учителя) {
        System.out.println("Список учителей:");
        for (Учитель учитель : учителя) {
            System.out.println("ID: " + учитель.getId() + ", Имя: " + учитель.getИмя() + ", Фамилия: " + учитель.getФамилия());
        }
    }
}


//И, наконец, создадим класс УчительКонтроллер (TeacherController), который будет объединять все действия с учителями:

import java.util.Scanner;

public class УчительКонтроллер {
    private УчительСервис учительСервис;
    private УчительВью учительВью;

    public УчительКонтроллер(УчительСервис учительСервис, УчительВью учительВью) {
        this.учительСервис = учительСервис;
        this.учительВью = учительВью;
    }

    public void начать() {
        Scanner scanner = new Scanner(System.in);
        boolean работает = true;

        while (работает) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать учителя");
            System.out.println("2. Редактировать учителя");
            System.out.println("3. Показать список учителей");
            System.out.println("4. Выйти");

            int выбор = scanner.nextInt();

            switch (выбор) {
                case 1:
                    System.out.println("Введите имя учителя:");
                    String имя = scanner.next();
                    System.out.println("Введите фамилию учителя:");
                    String фамилия = scanner.next();
                    учительСервис.создатьУчителя(имя, фамилия);
                    break;
                case 2:
                    System.out.println("Введите ID учителя для редактирования:");
                    int id = scanner.nextInt();
                    System.out.println("Введите новое имя:");
                    String новоеИмя = scanner.next();
                    System.out.println("Введите новую фамилию:");
                    String новаяФамилия = scanner.next();
                    учительСервис.редактироватьУчителя(id, новоеИмя, новаяФамилия);
                    break;
                case 3:
                    учительВью.показатьСписокУчителей(учительСервис.получитьСписокУчителей());
                    break;
                case 4:
                    работает = false;
                    break;
                default:
                    System.out.println("Неправильный выбор. Попробуйте еще раз.");
            }
        }
    }
}
