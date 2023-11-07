import React, { useState } from 'react'

import loginImg from '../../assets/1317336.jpg'
import './SignIn.scss'

import { Link, useNavigate } from 'react-router-dom'
import ClickHome from '../../components/ClickHome'
import api from '../../api'
import Requisicao from '../../components/ValidarToken'

export default function Login() {
  const [inputEmail, setInputEmail] = useState('');
  const [inputSenha, setinputSenha] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const navigate = useNavigate();

  function login(e) {
    e.preventDefault();

    const usuario = {
      email: inputEmail,
      senha: inputSenha
    };

    api.post('usuarios/login', usuario)
      .then(response => {
        // Tratar a resposta do servidor em caso de sucesso
        const token = response.data.token;
        
        // const token = 'valorDoToken';
        
        const nomeUsuario = response.data.nome; // Altere para o nome de usuário correto
        const idUsuario = response.data.id;

        localStorage.setItem('nome', nomeUsuario);
        localStorage.setItem('id', idUsuario);
        localStorage.setItem('token', token);
        // Requisicao.verificarAutenticacao();
        
        console.log('Login realizado com sucesso!', response.data);
        navigate('/'); 
        
      })
      .catch(error => {
        // Tratar a resposta do servidor em caso de erro
        console.error('Erro ao realizar login:', error);
      });
  }



  return (
    <div className="container">
      <div className="login_content">
        <img src={loginImg} alt="login image" className='login_img' />
        <div className="logo">
          <ClickHome />
        </div>
        <form className='login_form' onSubmit={login}>

          <div>
            <h1 className='login_title'>
              <span>Falta pouco</span> para você escolher sua próxima comida
            </h1>
            <p className='login_description'>
              Bem-vindo! Faça login para continuar
            </p>
          </div>

          <div>
            <div className="login_inputs">
              <div>
                <label htmlFor="" className='login_label'>Email</label>
                <input
                  type="email"
                  placeholder='insira seu endereço de e-mail'
                  required
                  className='login_input' 
                  onChange={(e) => setInputEmail(e.target.value)}
                />
              </div>

              <div>
                <label htmlFor="" className='login_label'>Senha</label>
                <div className="login_box">
                  <input type="password"
                    placeholder='insira sua senha'
                    required
                    className='login_input'
                    id='input-pass'
                    onChange={(e) => setinputSenha(e.target.value)}
                  />
                  <i className="ri-eye-off-line login_eye" id='input-icon'></i>
                </div>
              </div>
            </div>
          </div>

          <div className="login_check">
            <input type="checkbox" className='login_check_input' />
            <label htmlFor="" className='login_check_label'>lembrar usuário</label>
          </div>

          <div>
            <div className="login_buttons">
              <button className="login_button">entrar</button>
              <button className="login_button_ghost">
                <Link to="/cadastro">
                  cadastrar
                </Link>
              </button>
            </div>
          </div>

          <a href="#" className='login_forgot'>esqueci a senha</a>

        </form>
      </div>
    </div>
  )
}
