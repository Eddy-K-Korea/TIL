import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class Main {
    enum TestEnum {
//        A("a"),
        BCD("mode b"),
        CDF( "mode c");

        String getStatus() {
            return this.status;
        }
        final String status;
        TestEnum(String status) {
            this.status = status;
        }
    }
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {

        class Test implements Serializable {
            private String aBc;
            private String bCd;
            private String cDf;

            public Test(String a, String b, String c) {
                this.aBc = a;
                this.bCd = b;
                this.cDf = c;
            }
        }



        Test test = new Test("aaa", "bbb", "ccc");

        Field[] f = test.getClass().getDeclaredFields();

        for (int i = 0; i < f.length; i++) {
            f[i].setAccessible(true);
            System.out.println(f[i].get(test));
            System.out.println(f[i].getName());
        }

//        Class<?> cls = TestEnum.class;
//        for (int i = 0; i < TestEnum.values().length; i++) {
//            System.out.println(TestEnum.values()[i].getValue());
//        }

//        for (int i = 0; i < f.length; i++) {
//            f[i].setAccessible(true);
//            int finalI = i;
//            boolean result = Arrays.stream(TestEnum.values()).anyMatch(testEnum -> testEnum.toString().equals(f[finalI].getName().toUpperCase()));
//            if (result) {
//                if (f[i].get(test).toString().length() > 2) {
//                    System.out.println(TestEnum.valueOf(f[finalI].getName().toUpperCase()).getStatus());
//                }
//            }
//        }
    }
}