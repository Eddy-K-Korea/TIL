### 중복 하지 않는 값 산출 코드

로또, 랜덤 숫자를 여러번 뽑을때 한번 뽑았던 수를 다시 뽑지 않게하는 처리가 필요하다.
(중복되지 않은 값을 뽑아낼때)

아래 와 같이 코드를 구현하면 중복된 값을 막을 수 있다.

#### 1. 배열의 모든 값을 false 로 설정한다.
```
int r_num[] = new int[3];

boolean swit[] = new boolean[10];

for (int i = 0; i < swit.length; i++) {
  swit[i] = false;                  // 00000 00000  전부 false로 우선 설정
  }
```
예를 들어 length가 3인 배열에 랜덤 숫자를 넣는다고 할때 우선 boolean 자료형으로 배열을 생성한다.

#### 2. swit[]배열에 스위치 처럼 false 와 true를 이용하여 이용한 숫자인지 파악

```
int r, w; 			
w =0;
while(w<3) {
    r = (int)(Math.random()*10);		// r에 랜덤 숫자를 넣어준다.  나올 숫자도 맞게 설정
    if(swit[r] ==false) {				  
      swit[r] = true;
      r_num[w] = r;				      
      w++;
    }	
}
```
while문의 조건은 우선 내가 필요한 숫자의 개수가 3개이기 때문에 w<3으로 설정을 하였다. 

r에는 랜덤 숫자를 넣어주며 *뒤의 수는 0부터 9까지 총 10개의 수를 랜덤으로 대입 받는다.

이후 if 조건문의 조건은 swit[r] == false로 되어있는데 위 코드에서 이미 swit[]배열은 모두 false로 설정을 해놨기 때문에 진행된다.

그 이후 swit[r]의 **r은 랜덤숫자**로 배정받고 false에서 true로 변경이 되고 r의 숫자는 r_num의 배열로 대입된다.

```
swit[0] = false                                                   swit[0] = false
swit[1] = false                                                   swit[1] = false
swit[2] = false                                                   swit[2] = false
swit[3] = false                                                   swit[3] = false
swit[4] = false                 =>       swit[5] =true    =>      swit[4] = false        
swit[5] = false                                                  **swit[5] = ture**
swit[6] = false                                                   swit[6] = false
swit[7] = false                                                   swit[7] = false
swit[8] = false                                                   swit[8] = false
swit[9] = false                                                   swit[9] = false
```

if조건문으로 인해 한번 true로 바뀐 r 값에는 같은 값이 대입될 수 없다.

따라서 중복 되지 않는 수가 랜덤으로 전부 배정이 가능하다.
