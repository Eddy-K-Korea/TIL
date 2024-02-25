# Spring 개념

- 웹, 어플리케이션을 개발할때 사용하는 프레임 워크

이전에는 java ee (엔터프라이즈 에디션)과 java se (스탠다드 에디션)을 합쳐 트랜잭션을 관리하고 dao를 처리하였다.
하지만 java ee의 유료화가 되고 스프링이 잠식하게 되면서 웹의 기본적인 툴은 java se 와 스프링으로 자리잡혔다.

![캡처](https://user-images.githubusercontent.com/65350890/91311669-4ee3b500-e7ee-11ea-8a1c-f1a505e52afa.PNG)

MVC 모델 2방식으로 진행을하고 느슨한 결합력을 가지고 인터페이스를 사용한다.

- Spring 강의 start


# 결합력을 느슨하게 (인터페이스)

- DB에서 데이터를 끌어올때 사용하던 dao의 변경이 필요할때 코드를 수정하고 service와 새로 연결하면 코드의 수정이 발생하게된다.
- 이렇게 모듈간의 영향이 크게되면 유지보수에 힘들게된다(결합력이 높다)
- 좋은 어플리케이션은 결합력은 낮아야 좋다 (유지보수에 좋다)
- 아래 이미지처럼 원래 B라는 dao함수를 이용하고 있고 새로운 함수로 교체하려한다.

![캡처1](https://user-images.githubusercontent.com/65350890/91443019-b4e34180-e8ad-11ea-913b-f3866cc7c903.PNG)

- 아래 이미지 처럼 새롭게 B2 를 추가하려하지만 결국 service와 연결하기 위해서는 코드수정이 필요하다.
![캡처2](https://user-images.githubusercontent.com/65350890/91443024-b6146e80-e8ad-11ea-963e-5f540f764dba.PNG)

- 따라서 인터페이스를 통해 연결을하고 생성하는 객체는 그대로 두면서 새롭게 추가되거나 수정된 객체로 연결만 해주면된다.

![캡처3](https://user-images.githubusercontent.com/65350890/91443027-b7459b80-e8ad-11ea-8204-38f33e325cd5.PNG)

- 연결을 도와 주는것이 UI고 스프링에서 UI에서 연결(DI)를 쉽게 하도록 해준다

# DI(Defendency injection)

- 연결을 도와주는 대표적인 기능은 DI 와 IOC가 있다.
![캡처4](https://user-images.githubusercontent.com/65350890/91444770-3045f280-e8b0-11ea-86fb-d46f9ae72f71.PNG)

## DI
- 왼쪽은 스스로 생성하고 스스로 값을 가지고 오른쪽은 외부에서 값을 받아 setting한다

![캡처5](https://user-images.githubusercontent.com/65350890/91444771-30de8900-e8b0-11ea-84fc-f85b54331c83.PNG)

![캡처6](https://user-images.githubusercontent.com/65350890/91444763-2f14c580-e8b0-11ea-800d-686e5fe1d5aa.PNG)
- A라는 인터페이스를 만들어 놓고 객체를 생성하여 조립을 해주는 방식이 DI 이다. 이렇게되면 전체 코드 수정필요하지 않고 추가되는 코드만 조립해주면된다.

![캡처7](https://user-images.githubusercontent.com/65350890/91444769-3045f280-e8b0-11ea-91fc-1bd0b54bd5cd.PNG)
- set을 통해 값을 조립하는 경우와 생성시 값을 넣어 생성하여 조립하는 경우가 있다 이런 방식을 도와주는 것이 스프링이다.

## IOC container

- DI를 통해 부품조립을 하고 담는 부분이라고 할 수 있다. 그것을 컨테이너 라고 한다
![캡처1](https://user-images.githubusercontent.com/65350890/91567343-f3e1c780-e97f-11ea-8756-06a5beed735e.PNG)

- IOC 라고 불리는 이유는 일반 순서와 달리 부품을 조립할때 작은 객체부터 생성하고 큰객체를 생성하여 조립을 하는 순서 때문에 inversion of control이라 하여 IOC라고 불린다.
![캡처2](https://user-images.githubusercontent.com/65350890/91567348-f512f480-e97f-11ea-93d5-a2eeb39f8982.PNG)
