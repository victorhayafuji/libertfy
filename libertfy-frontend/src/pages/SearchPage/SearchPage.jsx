import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Navbar from '../../components/Navbar';
import { RiSearchLine } from 'react-icons/ri';
import { restaurantes, pratosStatic } from '../../Static';
import './SearchPage.scss';

export default function SearchPage() {
  const location = useLocation();
  const searchTerm = new URLSearchParams(location.search).get('query');

  const [inputPesquisa, setInputPesquisa] = useState('');
  const [queryType, setQueryType] = useState('pratos');
  const navigate = useNavigate();

  const handleSearch = (e) => {
    e.preventDefault();
    console.log('Realizando a pesquisa:', inputPesquisa);
  };

  const handleQueryTypeChange = (type) => {
    setQueryType(type);
  };

  const handleRestaurantClick = (id) => {
    navigate(`/restaurante/${id}`);
  };

  useEffect(() => {
    if (searchTerm === 'restaurantes' || searchTerm === 'pratos') {
      setQueryType(searchTerm);
    }
  }, [searchTerm]);

  return (
    <>
      <Navbar />
      <div className="containerSearch">
        <div className="resultado">
          <div className="title_page">
            <span>
              Procurando por <span className="red">"{searchTerm}"</span>
            </span>
          </div>

          <div className="result">
            <div className="seletor">
              <button
                className={queryType === 'pratos' ? 'active' : ''}
                onClick={() => handleQueryTypeChange('pratos')}
              >
                Pratos
              </button>
              <button
                className={queryType === 'restaurantes' ? 'active' : ''}
                onClick={() => handleQueryTypeChange('restaurantes')}
              >
                Restaurantes
              </button>
            </div>

            <div className="itens">
              {queryType === 'pratos' && (
                <>
                  {pratosStatic.map((item) => (
                    <div className="search_item" key={item.id}>
                      <img src={item.image} alt="" />
                      <div className="desc_item">
                        <span className="nome">{item.nome}</span>
                        <span className="especialidade">{item.descricao}</span>
                      </div>
                    </div>
                  ))}
                </>
              )}

              {queryType === 'restaurantes' && (
                <>
                  {restaurantes.map((item) => (
                    <div
                      className="search_item"
                      key={item.id}
                      onClick={() => handleRestaurantClick(item.id)}
                    >
                      <img src={item.image} alt="" />
                      <div className="desc_item">
                        <span className="nome">{item.name}</span>
                        <span className="especialidade">{item.type}</span>
                      </div>
                    </div>
                  ))}
                </>
              )}
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
