# React Props

### props는 리액트에서 속성으로 생각 할 수 있다.

### 예를 들어 Subject.js 컴포넌트에서 아래와 같은 소스가 있다.
<br>

## - Subject
```javascript
import React, { Component } from 'react';


//컴포넌트 만들기
// 1. 대문자로 시작해야한다.
// 2. Component 클래스 상속
// 3. render 함수를 생성하고 최상위 태그 하나만 존재 해야한다.
//props 사용하기
// 1. this.props.속성명
class Subject extends Component {
    render() {
        return (
            <header>
                <h1><a href="/">{this.props.title}</a></h1>
                {this.props.sub}
            </header>
        );
    }
}

export default Subject;
```
<br>

## - App.js
```javascript
render(){
    return (
      <div className="App">
          <Subject title="props 테스트" sub={this.state.subject.sub}></Subject>
          <TOC data={this.state.content}></TOC>
          <Content title={_title} desc={_desc}></Content>
      </div>
    );
}
```

### App.js의 Subject 컴포넌트에 title 속성에 값이 주어졌다. `<Subject title="props 테스트" sub={this.state.subject.sub}></Subject>`  Subject.js의 컴포넌트는 해당 속성을 전달 받아 실직적인 header태그의 텍스트로 페이지에 표시를 하게 된다. `<h1><a href="/">{this.props.title}</a></h1>` =>  `<h1><a href="/">props 테스트</a></h1>`