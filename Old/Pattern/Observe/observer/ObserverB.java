package observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverB implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		
		String msg = (String)arg;
		System.out.println("감시자B 입니다. 변화를 감지함 : "+msg);
	}

}
