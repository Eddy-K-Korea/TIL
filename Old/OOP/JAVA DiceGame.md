# DiceGame(주사위 게임)
## 조건
두개의 주사위 합을 맞히는 게임 제작 <br>
코인: 20개<br>
합을 맞추면 제시한 숫자에 따라서 배당금이 달라집니다.<br>
<br>
2, 12 : 제시할 수없음<br>
3, 11 : 18 배<br>
4, 10 : 12 배<br>
5, 9 : 9 배<br>
6, 8 : 7 배<br>
7 : 6 배<br>
<br>
배팅할 코인 수는? ------- 입력<br>
두 주사위의 합은? ------- 입력 2, 12를 입력하면 다시 입력<br>
두 주사위의 합은? ------- 6<br>
주사위 번호 출력<br>
<br>
결과 출력<br>

---
### Dice Class
- 주사위가 2개 이기 때문에 주사위(Dice)클래스를 따로 만들어서 랜덤값만을 주도록한다.
```java
public class Dice {
  
  private int number;
  
  public void diceRanNum() {
    number = (int)(Math.random()*6)+1;
  }
  
  public int getNumber()  {
    return number;
  }
}
```
### GamePlay Class
- GamePlay클래스에 사용자 입력, 조건판정, 결과 출력 등 구체적인 함수와 멤버변수를 넣어준다.
```java
public class GamePlay {
  
  private int coin;
  private int betting;
  private int InputNumber;
  
  // 우선 주사위 두개의 값을 받아야 하기 때문에 앞서 만들었던 Dice class의 인스턴스를 생성하도록 한다.
  
  Dice dice1 = new Dice();
  Dice dice2 = new Dice();
  
  // 이니셜라이즈 함수 (주사위 숫자 초기화 및 코인20개의 최초 초기화)
  public void init()  {
    coin = 20;
    dice1.diceRanNum();
    dice2.diceRanNum();
    
    //제대로 된 랜덤숫자가 나오는지 확인 (값을 얻어야 하기 때문에 getNumber() 메서드를 불러왔다.
    System.out.println("주사위1:"+dice1.getNumber());
		System.out.println("주사위2:"+dice2.getNumber());
  }
  
  // 사용자의 숫자 입력 메서드
  public void userInput() {
    Scanner sc = new Scanner(System.in);
    
    System.out.print("betting money = ");
		betting = sc.nextInt();
		
		coin = coin - betting;
		
		System.out.print("두개 주사위의 합 = ");
		inputNumber = sc.nextInt();
  }
  
  // 사용자숫자와 주사위 숫자 판정
  public void finding() {
    // 주사위 2개의 값의 합을 위해 입력
    int d_num1 = dice1.getNumber();
		int d_num2 = dice2.getNumber();
		
		int diceSum = d_num1 + d_num2;
		
    // 조건에 따른 판정
		if(inputNumber == diceSum) {
			if(diceSum == 3 || diceSum == 11) {
				betting = betting * 18;
			}
			else if(diceSum == 4 || diceSum == 10) {
				betting = betting * 12;
			}
			else if(diceSum == 5 || diceSum == 9) {
				betting = betting * 9;
			}
			else if(diceSum == 6 || diceSum == 8) {
				betting = betting * 7;
			}
			else if(diceSum == 7) {
				betting = betting * 6;
			}
			System.out.println("축하합니다.");
			coin = coin + betting;
		}
		else {
			System.out.println("아쉽습니다. 다시 도전해 주세요");
		}
  }
  // 주사위의 값 출력하여 사용자에게 알려주고 잔여 코인 출력
  public void result() {
    System.out.println("주사위1 : "+dice1.getNumber());
		System.out.println("주사위2 : "+dice2.getNumber());
		
		System.out.println("합계: "+(dice1.getNumber()+dice2.getNumber()));
		System.out.println("잔여코인: "+ coin);
  }
  // 외부 클래스에 순차적으로 넣는것 보다는 아래 방법으로 우선 셋팅
  public void gamePlay() {
    init();
		userInput();
		finding();
		result();
  }
```

### MainClass 
```java
public class MainClass {

	public static void main(String[] args) {

// 가장 기본적인 출력 방법
		GamePlay gp = new GamePlay();
		gp.gamePlay();
    
// 아래와 같이 실행 가능하다 (new GamePlay()) 가 인스턴트다.
    (new GamePlay()).gamePlay();
    
// GamePlay() 클래스에 생성자로 gamePlay() 함수를 넣어주고 클래스만 생성해서 바로 실행 해줄 수 있다.
    new GamePlay();   
    
   
    public GamePlay() {	 //메인에서 노출을 적게 하기 위해 GamePlay 클래스에 생성자 설정
		gamePlay();
	}
}   
