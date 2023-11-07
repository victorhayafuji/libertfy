import React, { useState, useEffect } from 'react';
import './Navbar.scss';
import { Link, useNavigate } from 'react-router-dom';
import logoImg from '../assets/vector-logo.png';
import api from '../api';
import queryString from 'query-string';

export default function Navbar() {
  const [isLogged, setIsLogged] = useState(false);
  const [username, setUsername] = useState('');

  const navigate = useNavigate();

  useEffect(() => {
    // const checkLoginStatus = async () => {
    //   try {
    //     const token = sessionStorage.getItem('token');
    //     if (token) {
    //       const response = await api.get('/usuarios/login', {
    //         headers: {
    //           Authorization: 'Bearer ' + token,
    //         },
    //       });
    //       setIsLogged(true);
    //       setUsername(response.data.nome);
    //     } else {
    //       // alert('Você não está logado');
    //       // navigate('/login');
    //     }
    //   } catch (error) {
    //     if (error.response && error.response.status === 403) {
    //       // Lógica para lidar com erro de autorização
    //       console.log('Error 403');
    //     }
    //     console.error('Erro ao obter perfil:', error);
    //   }
    // };

    // checkLoginStatus();

    const nomeUsuario = localStorage.getItem('nome');
    const idUsuario = localStorage.getItem('id');

    if (nomeUsuario && idUsuario) {
      setIsLogged(true);
      setUsername(nomeUsuario);
    } else {
      
      setIsLogged(false);
      setUsername('');
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('nome');
    localStorage.removeItem('id');
    setIsLogged(false);
    setUsername('');
    
    navigate('/login')
  };

  const handlePesquisar = (e) => {
    if (e.key === 'Enter') {
      const searchTerm = e.target.value.trim();
      if (searchTerm !== '') {
        navigate(`/search?query=${encodeURIComponent(searchTerm)}`);
      }
    }
  };

  const handlePratosClick = () => {
    const queryParams = queryString.stringify({ query: 'pratos' });
    navigate(`/search?${queryParams}`);
  };

  const handleRestaurantesClick = () => {
    const queryParams = queryString.stringify({ query: 'restaurantes' });
    navigate(`/search?${queryParams}`);
  };

  const handleSobreNos = () => {
    
    navigate(`/`);
  };
  

  return (
    <div className="nav_container">
      <div className="logo">
        <Link to={'/'}>
          <img src={logoImg} alt="logoImage" />
        </Link>
      </div>
      <div className="nav_links">
        <ul>
          <li onClick={handlePratosClick}>Pratos</li>
          <li onClick={handleRestaurantesClick}>Restaurantes</li>
          <li onClick={handleSobreNos} className='liSpecial'>Sobre nós</li>
          {/* <div className="search">
            <input type="text" placeholder="Buscar prato, restaurante" onKeyDown={handlePesquisar} />
          </div> */}
          {isLogged ? (
            <>
              <li>{username}</li>
              <li>
                <button className='btnLogoff' onClick={handleLogout}>Sair</button>
              </li>
            </>
          ) : (
            <>
              <li className="mark">
                <Link to="/cadastro">Criar conta</Link>
              </li>
              <li className="btnLogin">
                <Link to="/login">Entrar</Link>
              </li>
            </>
          )}
        </ul>
      </div>
    </div>
  );
}
