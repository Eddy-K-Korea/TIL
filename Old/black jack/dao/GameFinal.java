package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.CardDeck;

public class Game {

	List<Integer> cardList = new ArrayList<Integer>();
	ArrayList<Integer> uCardSet = null; // 유저 카드 set
	ArrayList<Integer> dCardSet = null; // 딜러 카드 set
	int userScore;
	int dealerScore;
	int replay = 1;
	int playerMoney = 1000;
	int bet;

	public void play() {
		System.out.println("==========Black Jack===========");
		betLoop1();
	}

	// 카드 생성

	public void init() {
		CardDeck cd = new CardDeck();
		cardList = cd.cardList();

		uCardSet.add(uCardDeck(cardList));
		uCardSet.add(uCardDeck(cardList));

		dCardSet.add(dCardDeck(cardList));
		dCardSet.add(dCardDeck(cardList));

	}

	// 카드 배분 (중복 숫자 제공 안됨)
	public int uCardDeck(List<Integer> cardList) {
		int index = (int) (Math.random() * 20);
		int num = cardList.get(index) % 13 + 1;
		int cardNum = changeScore(num, uCardSet);
		return cardNum;
	}

	public int dCardDeck(List<Integer> cardList) {
		int index = (int) (Math.random() * 20 + 20);
		int num = cardList.get(index) % 13 + 1;
		int cardNum = changeScore(num, dCardSet);
		return cardNum;
	}

	// 처음 점수 확인
	public boolean checkCard() {
		userSum(uCardSet);
		dealerSum(dCardSet);

		if (userScore == 21 && dealerScore == 21) {
			System.out.println("무승부");
			return false;
		} else if (userScore == 21 && dealerScore != 21) {
			System.out.println("Black Jack!!");
			System.out.println("User Win~~!!");
			playerMoney = playerMoney + bet * 2;
			System.out.println("획득한 돈: "+ bet * 2);
			System.out.println("플레이어 돈 : "+ playerMoney);
			return false;
		} else if (dealerScore == 21 && userScore != 21) {
			System.out.println("Dealer : Black Jack");
			System.out.println("User Lose...");
			playerMoney = playerMoney - bet;
			System.out.println("잃은 돈: "+ bet);
			System.out.println("플레이어 돈 : "+ playerMoney);
			return false;
		}
		return true;
	}

	// A, J, Q, K 점수 변환
	public int changeScore(int cardNum, List<Integer> cardList) {

		if (cardNum == 1) {
			int sum = 0;
			for (int i = 0; i < cardList.size(); i++) {
				sum = sum + cardList.get(i);
			}
			if (sum <= 10) {
				return 11;
			} else {
				return 1;
			}
		} else if (cardNum == 11) {
			return 10;
		} else if (cardNum == 12) {
			return 10;
		} else if (cardNum == 13) {
			return 10;
		}
		return cardNum;
	}
	// 카드 점수 합산
	public void userSum(ArrayList<Integer> uCardSet) {
		int sum = 0;
		for (int i = 0; i < uCardSet.size(); i++) {
			sum = sum + uCardSet.get(i);
			userScore = sum;
		}
	}

	public void dealerSum(ArrayList<Integer> dCardSet) {
		int sum = 0;
		for (int i = 0; i < dCardSet.size(); i++) {
			sum = sum + dCardSet.get(i);
			dealerScore = sum;
		}
	}
	
	public void betLoop1() {
		Scanner sc = new Scanner(System.in);
		loop1:while (replay == 1) {
			boolean b = true;
			uCardSet = new ArrayList<Integer>();
			dCardSet = new ArrayList<Integer>();

			while(true) {
				System.out.println("Player Money: " + playerMoney);
				System.out.print("베팅 금액 : ");
				bet = sc.nextInt();
				if(bet > playerMoney) {
					System.out.println("돈이 부족합니다. 금액을 다시 입력하세요");
				}
				else {
					break;
				}
			}
			// 카드 생성 및 카드 배분
			init();
			// 유저 카드 확인
			System.out.print("플레이어 카드: ");
			for (int i = 0; i < uCardSet.size(); i++) {
				System.out.print(uCardSet.get(i)+" ");
			}
			System.out.println();
			// 딜러 카드 확인
			System.out.print("딜러카드: ");
			System.out.print(dCardSet.get(0));
			
			System.out.println();
			// 점수 확인
			b = checkCard();

			hit_stayLoop2(b);
			
			if (playerMoney <= 0 && playerMoney < bet) {
				System.out.println("플레이어의 돈이 부족합니다.");
				while(true) {
					System.out.println("RePlay? Yes (1) / No (2)");
					System.out.print(">>> ");
					replay = sc.nextInt();
					if(replay == 1) {
						playerMoney = 1000;
						break;
					}
					else if(replay == 2) {
						System.out.println("감사합니다.");
						break loop1;
					}
					
					else {
						System.out.println("잘못된 입력 입니다. 다시 입력하세요");
					}
				}		
			}
			else {
				while(true) {
					System.out.println("RePlay? Yes (1) / No (2)");
					System.out.print(">>> ");
					replay = sc.nextInt();
					if(replay == 1) {
						break;
					}
					else if(replay == 2) {
						System.out.println("감사합니다.");
						break loop1;
					}
					
					else {
						System.out.println("잘못된 입력 입니다. 다시 입력하세요");
					}
				}	
			}
		}
	}
	
	public void hit_stayLoop2(boolean b) {
		Scanner sc = new Scanner(System.in);
		loop2: while (b) {
			System.out.println("Hit (1) / Stay (2)");
			System.out.print(">>>");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: // Hit
				// 카드 배분
				uCardSet.add(uCardDeck(cardList));
				userSum(uCardSet);
				if (dealerScore <= 16) {
					dCardSet.add(dCardDeck(cardList));
					dealerSum(dCardSet);
				}

				boolean b2 = scoreBrust();
				if(b2 == false) {
					break loop2;
				}
				System.out.print("플레이어 카드: ");
				for (int i = 0; i < uCardSet.size(); i++) {
					System.out.print(uCardSet.get(i)+" ");
					
				}
				System.out.println();
				System.out.println("점수 합산: " + userScore);
				
				break;
			case 2: // Stay
				// 카드 공개 및 점수 합산
				System.out.print("플레이어 카드: ");
				for (int i = 0; i < uCardSet.size(); i++) {
					System.out.print(uCardSet.get(i)+" ");
				}
				System.out.println();
				System.out.println("점수 합산: " + userScore);
				
				System.out.print("딜러 카드: ");
				for (int i = 0; i < dCardSet.size(); i++) {
					System.out.print(dCardSet.get(i)+" ");
				}
				System.out.println();
				System.out.println("점수 합산: " + dealerScore);
				
				if (userScore == dealerScore || userScore < dealerScore) {
					System.out.println("Player Lose...");
					playerMoney = playerMoney - bet;
					System.out.println("잃은 돈: "+ bet);
					System.out.println("플레이어 돈 : "+ playerMoney);
				} else if (userScore > dealerScore) {
					System.out.println("Player Win !!~");
					playerMoney = playerMoney + bet * 2;
					System.out.println("획득한 돈: "+ bet * 2);
					System.out.println("플레이어 돈 : "+ playerMoney);
				}
				break loop2;
			}
			
		}
	}
	
	public boolean scoreBrust() {
		if (userScore > 21) {
			System.out.print("플레이어 카드: ");
			for (int i = 0; i < uCardSet.size(); i++) {
				System.out.print(uCardSet.get(i)+" ");
			}
			System.out.println();
			System.out.println("점수 합산: " + userScore);
			System.out.println("Player Brust!");
			System.out.println("Player Lose...");
			playerMoney = playerMoney - bet;
			System.out.println("잃은 돈: "+ bet);
			System.out.println("플레이어 돈 : "+ playerMoney);
			return false;
		} else if (dealerScore > 21) {
			System.out.print("딜러 카드: ");
			for (int i = 0; i < dCardSet.size(); i++) {
				System.out.print(dCardSet.get(i)+" ");
			}
			System.out.println();
			System.out.println("점수 합산: " + dealerScore);
			System.out.println("dealer Brust!");
			System.out.println("Player Win !!~~");
			playerMoney = playerMoney + bet * 2;
			System.out.println("획득한 돈: "+ bet * 2);
			System.out.println("플레이어 돈 : "+ playerMoney);
			return false;
		}
		return true;
	}
}
