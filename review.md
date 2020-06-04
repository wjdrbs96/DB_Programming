# 상태정보유지기술

### 1) ServletContext, HttpSession, HttpServletRequest, Cookie 중에서 다음 질문에 답해주세요.

* 서버 사이드 저장 기술은 어느것들인가? <br>
```
ServletContext, HttpSession, HttpServletRequest
```

* HttpSession 키는 어디에 저장되어 있는지? <br>
```
서버 메모리에 저장
```

* Cookie의 옵션 중 Path와 MaxAge 기능 설명 <br>
```
path
- 서블릿마다 Cookie를 다르게 설정할 수 있음
- cookie.setPath("/원하는URL") : 원하는 URL로만 cookie를 보내도록 설정해서 효율적으로 Cookie관리를 한다.

MaxAge
- 쿠키의 만료 시간을 밀리초 단위로 설정
```

* 서버 사이드 저장 기술 대비 클라이언트 사이드 저장 기술의 장점? <br>
 ```
쿠키를 사용하면 request할 때 브라우저에 저장된 것을 같이 보내기 때문에 서버에 저장하고 있지 않아도 되기 때문에 저장 공간 절약
```

