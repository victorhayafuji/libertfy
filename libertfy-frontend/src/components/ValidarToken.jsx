// import api from '../api';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

// function Requisicao() {
//   // const navigate = useNavigate();  
//     var token = sessionStorage.getItem('token');

//     if (token) {
//       api
//         .get('/usuarios/login', {
//           headers: {
//             Authorization: 'Bearer ' + token,
//           },
//         })
//         .then(response => {
//           const token = response.data.token;
//           sessionStorage.setItem('token', token);
//         })
//         .catch((err) => {
//           if (err.response.status === 403) {
//             // Lógica para lidar com erro de autorização
//           }
//         });
//     } else {
//       alert('Você não está logado');
//       // navigate('/login');
//     }
//     return null;
//   };

//    // Pode retornar null ou qualquer outro elemento vazio, pois não será renderizado na interface


// export default Requisicao;

export default function Requisicao(nomeUsuario, idUsuario) {

  

  function verificarAutenticacao() {
    if ( idUsuario === "") {
      console.log("O usuário não está autenticado")
    } else {
      console.log("Usuário autenticado")
    }
  }

  useEffect(() => {
    verificarAutenticacao();
  }, [nomeUsuario, idUsuario]);

}