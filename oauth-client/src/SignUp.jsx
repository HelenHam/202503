import { useState, useRef } from 'react';
import Modal from '@/Modal.jsx';
import axios from 'axios';

const Reg = () => {
  const modalTitle = "회원 가입 하시겠습니까?";
  const [user, setUser] = useState({id: "", pwd: ""});
  const modalRef = useRef(null);
  let bootstrapModal = null;
  const showEvent = () => {
    import('bootstrap/dist/js/bootstrap.bundle.min.js').then((bootstrap) => {
      bootstrapModal = new bootstrap.Modal(modalRef.current, { backdrop: 'static' });
      if (bootstrapModal) {
        bootstrapModal.show();
        modalRef.current.addEventListener("hidePrevented.bs.modal", () => hideEvent());
      }
    });
  };
  const hideEvent = () => {
    if (bootstrapModal) {
      document.body.setAttribute("inert", "true");
      bootstrapModal.hide();
      modalRef.current.addEventListener(
        "hidden.bs.modal"
        , () => {document.body.removeAttribute("inert");}
        , { once: true }
      );
    }
  };
  const changeEvent = (e) => {
    const { name, value } = e.target;
    setUser({...user, [name]:value});
  }
  const submitEvent = (e) => {
    e.preventDefault();
    showEvent();
  }
  const asynEvent = () => {
    console.log("비동기 통신 요청");
    axios
      .post("http://localhost:8000/signUp", user)
      .then(res => {
        console.log(res);
          if (res.data === true) { 
          alert("회원가입 성공");
          localStorage.setItem("isPage", true);
          document.location.href = "/signIn2";
        } else {
          alert("회원가입 실패");
        }
      })
      .catch(err => console.log(err));
    hideEvent();
  }
  return (
    <>
      <div className="container my-3">
        <h1 className="text-center">가입</h1>
        <form onSubmit={submitEvent}>
          <div className="mb-3">
            <label htmlFor="id" className="form-label">아이디:</label>
            <input type="text" className="form-control" id="id" name="id" value={user.id} onChange={changeEvent} placeholder="아이디를 입력하세요." required autoComplete="off" />
          </div>
          <div className="mb-3">
            <label htmlFor="pwd" className="form-label">비밀번호:</label>
            <input type="password" className="form-control" id="pwd" name="pwd" value={user.pwd} onChange={changeEvent} placeholder="비밀번호를 입력하세요." required autoComplete="off" />
          </div>
          <div className="btn-group my-2 d-flex">
            <button type="submit" className="btn btn-primary flex-fill">등록</button>
            <a href="/signIn" className="btn btn-danger flex-fill">취소</a>
          </div>
        </form>
      </div>
      <Modal modalRef={modalRef} hideEvent={hideEvent} asynEvent={asynEvent} modalTitle={modalTitle}/>
    </>
  );
}

export default Reg;