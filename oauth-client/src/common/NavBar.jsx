import axios from 'axios';
import { useEffect, useState } from 'react';

const NavBar = () => {
  const [isPage, setPage] = useState(false);
  useEffect(() => {
      setPage((localStorage.getItem("isPage") == null)?false:true);
  }, []);
  const logout = () => {
    axios.post("http://localhost:8000/oauth/logout", {}, {withCredentials: true})
    .then(res => {
      if (res.data === true) { 
        localStorage.removeItem("isPage");
        alert("로그아웃 성공");
        document.location.href = "/signIn";
      } else {
        alert("로그아웃 실패");
      }
    })
  }
  return (
    <nav className="navbar" style={{"backgroundColor": "#2e2420"}}>
      <div className="container">
        <a className="navbar-brand" href="/" style={{"color": "#ffffff"}}>REACT</a>
        <ul className="nav nav-pills justify-content-end">
          <li className="nav-item">
          { isPage ?
            <button className="nav-link active" onClick={logout}>로그아웃</button>
            :
            <a className="nav-link active" href="/signIn">로그인</a>
          }
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default NavBar;