package reflect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description : 리플렉션 테스트
 *
 * @author skan
 * @since 2021/11/01
 */
class PersonTest {

    @Test
    public void whenGetIntegerFields_thenSuccess() throws Exception {
        Person person = new Person();

        Field ageField = person.getClass().getDeclaredField("age");
        ageField.setAccessible(true);
        int age = ageField.getInt(person);
        Assertions.assertEquals(30, age);
        ageField.set(person,(byte)31);

        // 생한 필드의 object 를 인자로 넣고, 필드의 실제 값을 가져온다.
        age = ageField.getByte(person);
        Assertions.assertEquals(31, age);

    }

    @Test
    public void nonNewInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Person person = Person.class.getDeclaredConstructor().newInstance();

        Arrays.stream(Person.class.getDeclaredFields()).forEach(field -> {
            System.out.println(field.getName());
            try {
                field.setAccessible(true);
                System.out.println(field.get(person));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }
}