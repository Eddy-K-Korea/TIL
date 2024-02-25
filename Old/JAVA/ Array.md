## Array (배열)


#### 정의 :   같은 자료형의 변수의 묶음
#### 관리 :   index number   0 부터 (n-1)개 까지
#### 목적 :   변수를 관리. 변수에 접근하기 위해서 사용
#### 형식 :   자료형  배열명(=변수명)[] = new 자료형[배열 갯수];

 - int Array[] = new int[10];  10개의 변수 선언 (0 ~9)      _동적 할당 후 정적으로 사용_
 
------↑STACK--------↑ heap------------  메모리에 저장 

 - int arr[] = {1, 2, 3};                             // 바로 변수를 초기화 하여 사용 할 수 있음
 
 #### 배열 참조(pointer) 
 
 (C언어의 포인터)
  - 장점 : 속도가 빠르다. 주소만 접근하면 모든 데이터를 접근 가능
  - 단점 : 보안에 취약하고 융통성이 자바 보다 낮다.
  
 ```
 int pArray[] = array; (위에 생성된 배열)
 pArray[] 에는 array 의 주소값만 들어가 있다.
 ex: array[] { 1,2,3,4,5}  일때
     pArray[0] = 6; 으로 했을때, array[0] = 6; 으로 변경이 된다.
     또한 pArray[3]의 값을 불러올때 array[3]의 값인 4 가 불러온다.
     
 
 따라서 pArray[] 의 배열을 불러올때 array[]배열의 값을 꺼내올 수 있다.

 ```
 
 ---
 
 ## 2차원 배열

```
int Array2[3][4] = {					         //첫 대괄호는 {}의 개수 , 두번째 대괄호는 {}안의 요소의 개수
    {1,2,3,4},
    {5,6,7,8},
    {9,10,11,12}
};		
```
 - 위의 배열은  int Array2[][] = new int[3][4]; 옆의 코드와 같이 구현이 가능하다.
 
 ```
for (int i = 0; i < Array2.length; i++) {			          //length는 3이다.
    for (int j = 0; j < Array2[i].length; j++) {		    //Array[0].length => 4이다.
      System.out.println("Array2["+ i + "]["+ j +"] = "+Array2[i][j]);
    }
}
```

- 메모리 저장 순서
1. Array2[0][0]
2. Array2[0][1]
3. Array2[0][2]
4. Array2[0][3]
     ……


## 2차원 배열 -> 1차원 배열로 바꾸기

우선 아래와 같이 임의의 2차원배열을 초기화 한다.
```
char array2[][] = {
{'A','B','C'},	
{'D','E','F'},	
{'G','H','I'},
{'J','K','L'}
};
```
그리고 2차원 배열의 length 함수를 통해 1차원 배열의 length를 지정해 줄 수 있다. (array2.length * array2[0].length)
```
char array1[] = new char[array2.length*array2[0].length];
```

왼쪽은 2차원 배열의 순서이며 오른쪽은 1차원 배열의 순서이다. 아래와 같은 순서로 값을 대입해주어야 한다.

이때 가운데 식을 보면 3을 array2배열의 length만큼 곱해주고 0 부터 1씩 증가하는 값을 더해주면 array1배열의 

index 넘버가 나오는것을 알 수 있다.
```
array2[0][0]	 3 * 0 + 0-> array1[0]
array2[0][1]	 3 * 0 + 1-> array1[1]
array2[0][2] 	 3 * 0 + 2-> array1[2]
	
array2[1][0]	 3 * 1 + 0-> array1[3]
array2[1][1]	 3 * 1 + 1-> array1[4]
array2[1][2]	 3 * 1 + 2-> array1[5]
```

이중 for 문을 이용하여 아까 식을 통해 1차원 배열에 값을 대입 해줄 수 있다. array2[i].length 는 3 

i 는 0 부터 작은 for 문이 끝날때마다 1씩 증가

j 는 0부터 1씩 증가 
```
for (int i = 0; i < array2.length; i++) {
   for (int j = 0; j < array2[i].length; j++) {
    array1[array2[i].length*i+j] = array2[i][j];
   }
}   
```
