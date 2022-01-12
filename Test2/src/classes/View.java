package classes;


/**
 * View class for MVC model
 */
public class View {

    /**
     * Just prints a string
     * @param str - string for print
     */
    public static void print(String str) {
        System.out.println(str);
    }
    public static void print(int i) {
        System.out.println(i);
    }

    public static void menu(String login) {
        print("Здравствуйте, "+login+". Что вы хотите сделать?");
        print("1 - Чтение данных из файла");
        print("2 - Добавление нового объекта");
        print("3 - Изменение объекта");
        print("4 - Удаление объекта");
        print("5 - Сохранение объектов");
        print("0 - Выход из программы");
    }
}
