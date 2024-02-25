package dto;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

	List<Integer> list = new ArrayList<Integer>();
	int r_num[] = new int[52];
	boolean swit[] = new boolean[52];
	
	public CardDeck() {
		for (int i = 0; i < swit.length; i++) {
			swit[i] = false;
		}
		int w = 0;
		while(w < r_num.length) {
			int r = (int) (Math.random() * 52);
			if (swit[r] == false) {
				swit[r] = true;
				r_num[w] = r;
				w++;
			}
		}
	}
	
	public List<Integer> cardList() {
		
		for (int i = 0; i < r_num.length; i++) {
			list.add(r_num[i]);
		}
		return list;
	}
}
