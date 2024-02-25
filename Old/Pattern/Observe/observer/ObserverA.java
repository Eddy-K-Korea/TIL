package observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverA implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		
		String str = (String)arg;
		System.out.println("감시자A 입니다. 통지 받았음 : "+str);
	}

	
}
