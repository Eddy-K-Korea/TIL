# (Dao) AllPrintClass
```java
import DaoInterface.DaoImpl;
import singleton.SingletonCls;

public class AllPrintClass implements DaoImpl {

	@Override
	public void process() {
		SingletonCls sc = SingletonCls.getInstance();

		for (int i = 0; i < sc.list.size(); i++) {
			System.out.println(sc.list.get(i).toString());
		}
	}
}
```
