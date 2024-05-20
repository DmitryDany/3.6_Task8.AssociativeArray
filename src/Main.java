import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String nameFact;
        int ageFact;

        Scanner inText = new Scanner(System.in);
        Scanner inInt = new Scanner(System.in);
        ArrayList<User> listObject = new ArrayList<>();
        int countUser = 0;
        for (int i = 0; i < 5; i++) {
            countUser++;
            System.out.println("Введите имя пользователя " + countUser);
            nameFact = inText.nextLine();
            System.out.println("Введите возраст пользователя " + countUser);
            ageFact = inInt.nextInt();
            User object = new User();
            object.setName(nameFact);
            object.setAge(ageFact);
            listObject.add(object);
        }
//        System.out.println(listObject);

        Stream<User> userStream = listObject.stream();
        HashMap<Integer, List<User>> mapa = (HashMap<Integer, List<User>>) userStream.collect(Collectors.groupingBy(User::getAge));

        System.out.println("Введите требуемый возраст");
        int ageNeed = inInt.nextInt();
        ArrayList<User> list = new ArrayList<>();
        if (mapa.containsKey(ageNeed)){
            list = (ArrayList<User>) mapa.get(ageNeed);
        } else {
            System.out.println("Пользователь с возрастом '" + ageNeed + "' не найден");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
    static class User{
        private String name;
        private Integer age;

        void setName(String name){
            this.name=name;
        }
        boolean correctAge(int arg){
            return (0 <= arg && arg <= 125);
        }
        void setAge(Integer age){
            if (correctAge(age)){
                this.age=age;
            }
        }
        String getName(){
            return name;
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public User() {
        }

        Integer getAge(){
            return age;
        }
        public String toString(){
            return String.format(name + ", возраст " + age + " лет");
        }
    }
}
class SortByAge implements Comparator<Main.User>{
    @Override
    public int compare(Main.User o1, Main.User o2) {
        return o1.getAge() - o2.getAge();
    }
}
