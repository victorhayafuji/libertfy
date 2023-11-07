import React from 'react';
import './Error404.scss';
import Navbar from '../../components/Navbar';

function Error404() {
  return (
    <div className="error-container">
      <Navbar />
      <div className="error-content">
        <h1 className="error-title">Erro 404</h1>
        <p className="error-message">Desculpe, a página que você está procurando não foi encontrada.</p>
      </div>
    </div>
  );
}

export default Error404;
