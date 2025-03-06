// import React, { useEffect } from 'react';
// import axios from 'axios';

// const UserInfo = () => {
//   const Event = () => {
    

//     axios
//     .post('http://localhost:8000/userInfo', {}, {withCredentials: true})// 쿠키를 포함하여 요청을 보냅니다.
//     .then(response => {
//         console.log(response.data);  // 서버로부터 받은 데이터를 처리합니다.
//       })
//       .catch(error => {
//         console.error('Error:', error);  // 에러 처리
//       });
//   }
  
//   return (
//   <div>
//     <h1>User Info</h1>
//     <form onSubmit={Event}>
//     <button type='submit'>버튼</button>
//     </form>
//   </div>
//   )
// };

// export default UserInfo;