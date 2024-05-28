import java.util.*;
import java.text.SimpleDateFormat;

public class Person {
    String name;
    int age;
    List<String> hobbies;
    Date timestamp;

    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ")";
    }

    private String TimestampConverter(Date ts){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(ts);
    }

    public String print(){
        return name + " (Timestamp: " + TimestampConverter(timestamp) + ")";
    }
}
