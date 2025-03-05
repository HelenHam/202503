import { useState } from 'react';
import axios from 'axios';

const SignIn = () => {
  const [user, setUser] = useState({id: "oidc-client", pwd: "secret"});
  const changeEvent = (e) => {
    const {name, value} = e.target;
    setUser({...user, [name]:value});
  }
  const submitEvent = (e) => {
    e.preventDefault();
    console.log("비동기 통신 요청");
    axios
      .post("http://localhost:8000/signIn2", user, {withCredentials: true})
      .then(res => {
        console.log(res);
          if (res.data === true) { 
          alert("로그인 성공");
          localStorage.setItem("isPage", true);
          document.location.href = "/";
        } else {
          alert("로그인 실패");
        }
      })
      .catch(err => console.log(err));
  }
  return (
    <div className="container my-3">
      <h1 className="text-center">로그인</h1>
      <form onSubmit={submitEvent}>
        <div className="mb-3">
          <label htmlFor="id" className="form-label">아이디:</label>
          <input type="text" className="form-control" id="id" name="id" value={user.id} onChange={changeEvent} />
        </div>
        <div className="mb-3">
          <label htmlFor="pwd" className="form-label">비밀번호:</label>
          <input type="password" className="form-control" id="pwd" name="pwd" value={user.pwd} onChange={changeEvent} />
        </div>
        <div className="btn-group my-2 d-flex">
          <button type="submit" className="btn btn-primary flex-fill">로그인</button>
          <a href="/signUp" className="btn btn-success flex-fill">회원가입</a>
          <a href="/" className="btn btn-danger flex-fill">취소</a>
        </div>
      </form>
    </div>
  );
}

export default SignIn;