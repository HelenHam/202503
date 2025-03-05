import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '@/index.css';
import App from '@/App.jsx';
import NavBar from '@coms/NavBar.jsx';
import SignIn from '@/SignIn.jsx';
import SignUp from '@/SignUp.jsx';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <NavBar />
    {/* 심플 페이지 애플리케이션 SPA (프론트 기술은 대부분이 이것) */}
    <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />

          <Route path="*" element={<App />} />
          {/* 접근 불가에 대한 애용 */}
        </Routes>
    </BrowserRouter>
  </StrictMode>
)
