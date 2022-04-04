import java.util.*;

public class User {
    //объявляем поля, мапу с пользователями, счетчик id
    private int id;
    private String name;
    private int age;
    private Sex sex;
    private static Map<Integer, User> allUsers;
    private static int countId = 0;

    //геттеры под все поля
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    //конструктор инициализирует поля; создает мапу, если ее еще нет; присваивает уникальный id пользователю с помощью hasUser()
    public User(String name, int age, Sex sex) {

        if (allUsers == null){
            allUsers = new HashMap<>();
        }

        this.name = name;
        this.age = age;
        this.sex = sex;

        if (!hasUser()){
            countId++;
            this.id = countId;
            allUsers.put(id, this);
        }
    }

    //переопределяем методы для hasUser()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                sex == user.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    //метод, который позволяет создавать уникальных пользователей
    private boolean hasUser() {
        for (User user : allUsers.values()){
            if (user.equals(this) && user.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    //возвращет список всех пользователей
    public static List<User> getAllUsers(){
        return new ArrayList<>(allUsers.values());
    }

    //возвращет список всех пользователей по нужному полу
    public static List<User> getAllUsers(Sex sex){
        List<User> listAllUsers = new ArrayList<>();
        for (User user : allUsers.values()){
            if (user.sex == sex){
                listAllUsers.add(user);
            }
        }
        return listAllUsers;
    }

    //возвращает число пользователей в мапе
    public static int getHowManyUsers(){
        return allUsers.size();
    }

    //возвращает число пользователей в мапе в зависимости от пола
    public static int getHowManyUsers(Sex sex){
        return getAllUsers(sex).size();
    }

    //сумма возрастов всех пользователей
    public static int getAllAgeUsers(){
        int countAge = 0;
        for (User user : allUsers.values()){
            countAge += user.age;
        }
        return countAge;
    }

    //сумма возрастов всех пользователей в зависимости от пола
    public static int getAllAgeUsers(Sex sex){
        int countAge = 0;
        for (User user : getAllUsers(sex)){
            countAge += user.age;
        }
        return countAge;
    }

    //средний возраст пользователей
    public static int getAverageAgeOfAllUsers(){
        return getAllAgeUsers() / getHowManyUsers();
    }

    //средний возраст пользователей в зависимости от возраста
    public static int getAverageAgeOfAllUsers(Sex sex){
        return getAllAgeUsers(sex) / getHowManyUsers(sex);
    }

}
