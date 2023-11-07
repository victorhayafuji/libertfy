import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import Navbar from '../../components/Navbar'
import './Restaurante.scss'
import { FaTrash, FaEdit } from 'react-icons/fa';
import { restaurantes, pratosStatic } from '../../Static'
import imgTeste from "../../assets/background-restaurante.jpg"
import { FaStar } from 'react-icons/fa'
import iconRestaurante from '../../assets/logo-restaurante-teste.jpg'
import Comentario from '../../components/Comentario'
import api from '../../api'



export default function Restaurante() {
  
  const { id } = useParams();
  // const [restauranteId, setRestauranteId] = useState(null);
  const [restaurante, setRestaurante] = useState(null);

  const stars = Array(5).fill(0);
  const [userAvaliacao, setUserAvaliacao] = useState(0)
  const [currentValue, setCurrentValue] = useState(0);
  const [hoverValue, setHoverValue] = useState(undefined);
  const [showButton, setShowButton] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const [mediaAvaliacao, setMediaAvaliacao] = useState(0);

  const [comentario, setComentario] = useState('')
  const [comentarios, setComentarios] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    
    
  }, []);



  useEffect(() => {
    const fetchData = async () => {
      try {
        // setRestauranteId(id);
        const responseRestaurante = await api.get(`http://localhost:8080/restaurantes/${id}`);
        setRestaurante(responseRestaurante.data);

        const token = localStorage.getItem('token');
        setIsLoggedIn(token ? true : false);

        const responseComentarios = await api.get(`http://localhost:8080/restaurantes/${id}/comentarios`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        setComentarios(responseComentarios.data)

         // Calcular a média de avaliação
        const totalAvaliacoes = responseComentarios.data.length;
        const somaAvaliacoes = responseComentarios.data.reduce((total, comentario) => total + comentario.avaliacao, 0);
        const media = totalAvaliacoes > 0 ? somaAvaliacoes / totalAvaliacoes : 0;
      } catch (error) {
        console.error('Error ao obter informações:', error)
      }
    };

    fetchData();
  }, [])

  const handleVoto = async () => {
    console.log('Avaliação enviada com sucesso:', userAvaliacao);
    
    // try {
    //   const response = await api.post('URL_DA_API/avaliacoes', { rating: userAvaliacao });
    //   console.log('Avaliação enviada com sucesso:', response.data);
    //   // Atualize o estado de avaliações com a nova avaliação
    //   setComentarios([...comentarios, response.data]);
    // } catch (error) {
    //   console.error('Erro ao enviar a avaliação:', error);
    // }

    try {
      const token = localStorage.getItem('token');
  
      if (!token) {
        alert('Usuário não está logado. Por favor, faça login para continuar');
        navigate('/login');
        return;
      }
  
      if (userAvaliacao === 0) {
        alert('Por favor, selecione uma avaliação.');
        return;
      }
  
      const novoComentario = {
        comentario: '',
        avaliacao: userAvaliacao,
        usuarioId: localStorage.getItem('id'),
        restauranteId: id,
      };
  
      const response = await api.post('http://localhost:8080/comentarios', novoComentario, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
  
      console.log('Avaliação enviada com sucesso:', response.data);
      setUserAvaliacao(userAvaliacao);
      setShowButton(false);
    } catch (error) {
      console.error('Erro ao enviar a avaliação:', error);
    }
  };

  const handleClickVoto = value => {
    setUserAvaliacao(value);
    setShowButton(true);
    setCurrentValue(value);
  };
  

  const handleComentarioChange = (e) => {
    setComentario(e.target.value);
  };



  const handleEnviarComentario = async () => {

    // if (!isLoggedIn) {
    //   alert('Usuário não está logado. Por favor faça login para continuar');
    //   navigate("/login");
    //   return;
    // }

    const token = localStorage.getItem('token');

    if (!token) {
      alert('Usuário não está logado. Por favor, faça login para continuar');
      navigate('/login');
      return;
    }

    if (!comentario) {
      alert('Por favor, digite um comentário.');
      return;
    }

    // const dataAtual = new Date();
    // const dia = String(dataAtual.getDate()).padStart(2, '0');
    // const mes = String(dataAtual.getMonth() + 1).padStart(2, '0'); // Mês começa a partir de 0
    // const ano = String(dataAtual.getFullYear());
    // const dataFormatada = `${dia}/${mes}/${ano}`;

    const novoComentario = {
      // id: comentarios.length + 1,
      // nome: localStorage.getItem('nome'),
      comentario: comentario,
      avaliacao: userAvaliacao,
      usuarioId: localStorage.getItem('id'),
      restauranteId: 1,
      
    };

    try {
      const token = localStorage.getItem('token'); // Obtém o token de autenticação do armazenamento local

    const response = await api.post('http://localhost:8080/comentarios', novoComentario, {
      headers: {
        Authorization: `Bearer ${token}` // Inclui o token de autenticação no cabeçalho da requisição
      }
    });
      console.log('Comentário enviado com sucesso:', response.data);
      setComentarios([...comentarios, response.data]);
      setComentario('');
    } catch (error) {
      console.error('Erro ao enviar comentário:', error)
    }

    // setComentarios([...comentarios, novoComentario]);
    // setComentario('');
  };

  const handleExcluirComentario = async (id) => {
    try {
      const token = localStorage.getItem('token'); // Obtém o token de autenticação do armazenamento local
  
      // Faz a requisição para excluir o comentário com o ID fornecido
      await api.delete(`http://localhost:8080/comentarios/${id}`, {
        headers: {
          Authorization: `Bearer ${token}` // Inclui o token de autenticação no cabeçalho da requisição
        }
      });
      const updatedComentarios = comentarios.filter(comentario => comentario.id !== id);
      setComentarios(updatedComentarios);
      console.log('Comentário excluído com sucesso');
    } catch (error) {
      console.error('Erro ao excluir o comentário:', error)
    }
    
  };

  const handleEditarComentario = async (id, novoComentario) => {
    try {
      const token = localStorage.getItem('token'); // Obtém o token de autenticação do armazenamento local

    // Faz a requisição para editar o comentário com o ID fornecido
    const response = await api.put(`http://localhost:8080/comentarios/${id}`, { comentario: novoComentario }, {
      headers: {
        Authorization: `Bearer ${token}` // Inclui o token de autenticação no cabeçalho da requisição
      }
    });

    const updatedComentarios = comentarios.map(comentario => {
      if (comentario.id === id) {
        return { ...comentario, comentario: novoComentario };
        // return { ...comentario, comentario: response.data.comentario };
      }
      return comentario;
    });
    setComentarios(updatedComentarios);
    console.log('Comentário editado com sucesso')
    } catch (error) {
      console.error('Erro ao editar comentário:', error)
    }
  }

  const colors = {
    yellow: "#e9e514",
    grey: "#A9A9A9",
    lowYellow: "#e9e6146a"
  }

  const handleClick = value => {
    setCurrentValue(value)
    setShowButton(true)
  }

  const handleMouseOver = value => {
    setHoverValue(value)
  }

  const handleMouseLeave = () => {
    setHoverValue(undefined)
  }

  return (

    <>
      <Navbar />
      <div className='restauranteContainer'>
        <div className="banner">
          <img src={imgTeste} alt="" />
        </div>

        <div className="content">

          <div className="faixada_restaurante">
            <div className="left">
              <h1 className='restaurante_titulo'>{restaurante.nome}</h1>
              <div className="avaliacao">
                {stars.map((_, index) => {
                  return (
                    <FaStar
                      key={index}
                      size={25}
                      style={{
                        marginTop: 35,
                        marginRight: 15,
                        cursor: "pointer",
                        
                      }}
                      // color={(hoverValue || userAvaliacao) > index ? colors.yellow : colors.grey}
                      // color={(hoverValue || currentValue) >= index + 1 ? colors.yellow : colors.grey}
                      color={(restaurante?.mediaAvaliacao || 0) > index ? colors.yellow : colors.grey}
                      // color={colors.lowYellow}
                    />
                  );
                })}
                <div className="qtd">
                {/* {comentarios.length} {comentarios.length === 1 ? 'avaliação' : 'avaliações'} */}
                {restaurante?.numeroAvaliacoes || 0} avaliações
                </div>
              </div>
            </div>
            <img src={iconRestaurante} alt="restaurante_logo" />
          </div>

          <div className='descricao_restaurante'>
            Descubra a magia da culinária asiática em nosso restaurante. Oferecemos uma grande variedade de pratos autênticos que vão desde os tradicionais pratos de noodles até os famosos sushi rolls. Todos os nossos pratos são preparados com ingredientes frescos e autênticos, e nossa equipe está pronta para tornar a sua experiência conosco a melhor possível. Venha nos visitar e descubra um mundo de sabores!
          </div>
          <div className="infos_restaurante">
            <h2>Informações do restaurante</h2>
            <span>Culinária {restaurante?.especialidade}</span>
            <span>Telefone: {restaurante?.telefone}</span>
            {/* <span>Seg. à Sexta: 17h - 23h , Finais de Semana: 11h - 00h</span> */}
            <span>Endereço: {restaurante?.endereco}</span>
          </div>
          <div className="diviser"></div>
          <h1 style={{
            marginTop: '50px'
          }}>Pratos principais</h1>
          <div className="pratos">

            {pratosStatic.map((prato, id) => {
              return (
                <div className="prato" key={id}>
                  <div className="img">
                    <img src={prato.image} alt="imagem prato" />
                  </div>
                  <div className="info">
                    <h2>{prato.nome}</h2>
                    <span className="descricao_prato">
                      {prato.descricao}
                      <div className="line"></div>
                    </span>
                  </div>

                </div>
              )
            })}



          </div>
          <div className="estrelasContainer">
            <h2>Já conhece o restaurante?</h2>
            <h2 className='mark'>Faça uma avaliação</h2>
            <span>Diz pra gente quantas estrelas o restaurante merece:</span>
            <div className="estrelas">
              {stars.map((_, index) => {
                return (
                  <FaStar
                    key={index}
                    size={45}
                    style={{
                      marginTop: 35,
                      marginRight: 15,
                      cursor: "pointer"
                    }}
                    color={(hoverValue || currentValue) >= index + 1 ? colors.yellow : colors.grey}
                    onClick={() => handleClickVoto(index + 1)}
                    onMouseOver={() => handleMouseOver(index + 1)}
                    onMouseLeave={handleMouseLeave}
                  />
                )
              })}
            </div>
            {/* {showButton ? (
                <button className='btn_votar' onClick={handleVoto}>Avaliar</button>
              ) : null
            } */}
            {!showButton && userAvaliacao > 0 ? (
              <div className="avaliacaoFixa">
                <span>Avaliação: {userAvaliacao}</span>
              </div>
            ) : (
              <button className="btn_votar" onClick={handleVoto}>Avaliar</button>
            )}
          </div>

          <div className="comentarios">
            <div className="div1">
              <h3>Comentários</h3>
              <span>Deixe seu feedback</span>
              <div className="novoComentario">
                <textarea
                  autoComplete="off"
                  rows="1"
                  maxLength="2000"
                  // id="comment"
                  // className="comment-input"
                  // rows="5"
                  // placeholder="Digite seu comentário aqui..."
                  value={comentario}
                  onChange={handleComentarioChange}
                />

                <button onClick={handleEnviarComentario}>Enviar</button>
              </div>
            </div>

            <div className="resultado">
              {
                comentarios.length === 0 ? (
                  <p>O restaurante ainda não recebeu avaliações.</p>
                ) : (
                  comentarios.map((comentario) => (
                    <Comentario
                      key={comentario.id}
                      id={comentario.id}
                      nome={comentario.nome}
                      voto={comentario.voto}
                      comentario={comentario.comentario}
                      data={comentario.data}
                      onExcluirComentario={handleExcluirComentario}
                      onEditarComentario={handleEditarComentario}
                    >
                      
                    </Comentario>
                  ))
                )}
            </div>

          </div>
        </div>
      </div>
    </>
  )
}
