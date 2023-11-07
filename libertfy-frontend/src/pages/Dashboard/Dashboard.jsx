import React, { useState, useEffect, useRef } from 'react';
import api from '../../api';
import Chart from 'chart.js/auto';
import './styles.css';
import Navbar from '../../components/Navbar';

const Dashboard = () => {
  const idadeChartRef = useRef(null);
  const generoChartRef = useRef(null);
  const avaliacaoChartRef = useRef(null);
  const [generoChartData, setGeneroChartData] = useState([0, 0]);

  useEffect(() => {
    const initializeCharts = () => {
      const idadeChartCanvas = document.getElementById('idade-chart');
      const generoChartCanvas = document.getElementById('genero-chart');
      const avaliacaoChartCanvas = document.getElementById('avaliacao-chart');

      idadeChartRef.current = new Chart(idadeChartCanvas, {
        type: 'bar',
        data: {
          labels: ['Média de Idade'],
          datasets: [
            {
              label: 'Média de idade',
              data: [0],
              backgroundColor: ['#BF2A2A', '#f28322', 'rgba(75, 192, 192, 0.6)'],
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
        },
      });

      generoChartRef.current = new Chart(generoChartCanvas, {
        type: 'pie',
        data: {
          labels: ['Homem', 'Mulher'],
          datasets: [
            {
              label: 'Gênero',
              data: generoChartData,
              backgroundColor: ['#BF2A2A', '#f28322'],
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
        },
      });

      avaliacaoChartRef.current = new Chart(avaliacaoChartCanvas, {
        type: 'bar',
        data: {
          labels: ['Média de Avaliação'],
          datasets: [
            {
              label: 'Avaliação',
              data: [],
              backgroundColor: '#f28322',
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
        },
      });

      fetchDataAndUpdateCharts();
    };

    initializeCharts();

    // Cleanup function
    return () => {
      if (idadeChartRef.current) {
        idadeChartRef.current.destroy();
      }
      if (generoChartRef.current) {
        generoChartRef.current.destroy();
      }
      if (avaliacaoChartRef.current) {
        avaliacaoChartRef.current.destroy();
      }
    };
  }, []);

  const fetchDataAndUpdateCharts = () => {
    api.get('http://localhost:8080/usuarios/media-idade')
      .then(response => {
        if (idadeChartRef.current) {
          idadeChartRef.current.data.datasets[0].data = [response.data];
          idadeChartRef.current.update();
        }
      })
      .catch(error => {
        console.log(error);
      });

      api.get('http://localhost:8080/usuarios/percentual-masculinos')
      .then(response => {
        const percentualMasculino = response.data;
        const percentualFeminino = 100 - percentualMasculino;

        if (generoChartRef.current) {
          generoChartRef.current.data.datasets[0].data = [percentualMasculino, percentualFeminino];
          generoChartRef.current.update();
        }
      })
      .catch(error => {
        console.log(error);
      });



    api.get('http://localhost:8080/comentarios/media-avaliacao')
      .then(response => {
        if (avaliacaoChartRef.current) {
          avaliacaoChartRef.current.data.datasets[0].data = response.data;
          avaliacaoChartRef.current.update();
        }
      })
      .catch(error => {
        console.log(error);
      });
  };

  function downloadTXT() {
    window.location.href = 'http://localhost:8080/usuarios/download-txt';
  }

  function uploadFile() {
    api.post('http://localhost:8080/usuarios/upload/1', {})
      .then(response => {
        console.log('Arquivo enviado com sucesso!');
      })
      .catch(error => {
        console.log(error);
      });
  }

  return (
    
    <div className='containerDash'>
      <Navbar />
      {/* <nav className="sidebar" tabIndex="0">
        <div className="menu-toggle">
          <span></span>
          <span></span>
          <span></span>
        </div>
        <nav className="menu">
          <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">Sobre</a></li>
            <li><a href="#">Serviços</a></li>
            <li><a href="#">Contato</a></li>
          </ul>
        </nav>
      </nav> */}
      <div className="dashboard">
        <div className="chart-container">
          <canvas id="idade-chart" />
        </div>
        <div className="chart-container">
          <canvas id="genero-chart" />
        </div>
        {/* <div className="chart-container">
          <canvas id="avaliacao-chart" />
        </div> */}
        <div className="containerBtns">
        <button className="custom-button" onClick={downloadTXT}>Download TXT</button>
        {/* <button className="custom-button" onClick={uploadFile}>Upload</button> */}
      </div>
      </div>
      
    </div>
  );
};

export default Dashboard;