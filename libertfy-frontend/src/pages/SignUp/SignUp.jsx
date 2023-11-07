import React, { useState } from 'react';
import './SignUp.scss';

import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup';
import { object, string, date } from "yup";



import cadastroImg from '../../assets/2006004.jpg';

import ClickHome from '../../components/ClickHome';
import api from '../../api';

import { Link, useNavigate } from 'react-router-dom';

export default function SignUp() {
  
  const schema = object({
    email: string()
      .required("Campo obrigatório")
      .email("Formato de email inválido"),
    nome: string()
      .required("Campo obrigatório")
      .min(3, "Você precisa inserir pelo menos 3 caracteres.")
      .max(50, "Seu nome pode ter no máximo 50 caracteres"),
    senha: string()
      .required("Campo obrigatório")
      .min(6, "Sua senha precisa ter no mínimo 6 caracteres"),
    dataNascimento: date()
    .required("Campo obrigatório")
    .typeError("Formato de data inválido")
    .test(
      "validade-data-nascimento",
      "Você deve ter pelo menos 18 anos",
      (value) => {
        const currentDate = new Date();
        const selectedDate = new Date(value);
        const eighteenYearsAgo = new Date(
          currentDate.getFullYear() - 18,
          currentDate.getMonth(),
          currentDate.getDate()
        );
        return selectedDate <= eighteenYearsAgo;
      }
    ),
    genero: string()
      .required("Campo obrigatório"),
  })

  const {
    register,
    formState: { errors },
    handleSubmit,
    watch
  } = useForm({ resolver: yupResolver(schema) });

  const navigate = useNavigate();

  const onSubmit = (data) => {
    // console.log(data);
    const novoUsuario = {
      nome: data.nome,
      email: data.email,
      senha: data.senha,
      dataNascimento: data.dataNascimento,
      genero: data.genero,
    };

  console.log('Usuário cadastrado: ', novoUsuario);

  api
    .post('usuarios/criar', novoUsuario)
    .then((response) => console.log(response))
    .catch((error) => console.error(error.response));

  navigate('/login');
}



  // function cadastrar(e) {
  //   e.preventDefault();

  //   if(validarSenha()) {
  //     const novoUsuario = {
  //       nome: inputNome,
  //       email: inputEmail,
  //       senha: inputSenha,
  //       dataNascimento: inputDtNascimento,
  //       genero: inputGenero,

  //     };

  //     console.log('Usuário cadastrado: ', novoUsuario);

  //     api
  //       .post('usuarios/criar', novoUsuario)
  //       .then((response) => console.log(response))
  //       .catch((error) => console.error(error.response));

  //     navigate('/login');
  //   }


  // }

  return (
    <div className="container">
      <div className="cadastro_content">
        <img src={cadastroImg} alt="cadastro image" className="cadastro_img" />
        <div className="logo">
          <ClickHome />
        </div>
        <form onSubmit={handleSubmit(onSubmit)} className="cadastro_form">
          <div>
            <h1 className="cadastro_title">
              <span>Novo aqui?</span> então faça já o seu cadastro
            </h1>
          </div>

          <div>
            <div className="cadastro_inputs">
              <div>
                <label htmlFor="" className="cadastro_label">
                  Email
                </label>
                <input
                  className="cadastro_input"
                  {...register("email")}
                />
                <span className='error'>{errors?.email?.message}</span>
              </div>

              <div>
                <label htmlFor="" className="cadastro_label">
                  Nome
                </label>
                <input
                  type="text"
                  className="cadastro_input"
                  {...register("nome")}
                />
                <span className='error'>{errors?.nome?.message}</span>
              </div>

              <div>
                <label htmlFor="" className="cadastro_label">
                  Data nascimento
                </label>
                <input
                  type="date"
                  className="cadastro_input"
                  {...register("dataNascimento")}
                />
                <span className='error'>{errors?.dataNascimento?.message}</span>
              </div>

              <div>
                <label htmlFor="" className="cadastro_label">
                  Gênero
                </label>
                <select
                  className="cadastro_mask_input"
                  {...register("genero")}
                >
                  <option value="">Selecione</option>
                  <option value="masculino">Masculino</option>
                  <option value="feminino">Feminino</option>
                </select>
                <span className='error'>{errors?.genero?.message}</span>
              </div>





              <div>
                <label htmlFor="" className="cadastro_label">
                  Senha
                </label>
                <div className="cadastro_box">
                  <input
                    type="password"
                    className="cadastro_input"
                    {...register("senha")}
                  />
                  <i className="ri-eye-off-line cadastro_eye" id="input-icon"></i>
                </div>
                <span className='error'>{errors?.senha?.message}</span>
              </div>
            </div>
          </div>

          <div className="cadastro_check">
            <input type="checkbox" className="cadastro_check_input" />
            <label htmlFor="" className="cadastro_check_label">
              lembrar usuário
            </label>
          </div>

          <div>
            <div className="cadastro_buttons">
              <button
                className="cadastro_button_ghost"
              >
                cadastrar
              </button>
            </div>
          </div>

          <Link to="/login" className="cadastro_forgot">
            já possui cadastro?
          </Link>
        </form>
      </div>
    </div>
  );
}