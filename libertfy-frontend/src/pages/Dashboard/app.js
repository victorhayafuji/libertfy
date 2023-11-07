function fetchDataAndUpdateCharts() {
    axios.get('http://localhost:8080/usuarios/media-idade')
      .then(function (response) {
        // Atualize os dados do gráfico de idade
        idadeChart.data.datasets[0].data = [response.data];
        idadeChart.update();
      })
      .catch(function (error) {
        console.log(error);
      });
  
    axios.get('http://localhost:8080/usuarios/percentual-masculinos')
      .then(function (response) {
        // Atualize os dados do gráfico de gênero
        generoChart.data.datasets[0].data = [response.data];
        generoChart.update();
      })
      .catch(function (error) {
        console.log(error);
      });
  
    axios.get('http://localhost:8080/usuarios/percentual-femininos')
      .then(function (response) {
        // Atualize os dados do gráfico de gênero
        generoChart.data.datasets[1].data = [response.data];
        generoChart.update();
      })
      .catch(function (error) {
        console.log(error);
      });
  
    axios.get('http://localhost:8080/comentarios/media-avaliacao')
      .then(function (response) {
        // Atualize os dados do gráfico de avaliação
        avaliacaoChart.data.datasets[0].data = [response.data];
        avaliacaoChart.update();
      })
      .catch(function (error) {
        console.log(error);
      });
  }
  
  // Função para lidar com o clique no botão Executar
  function handleButtonClick() {
    fetchDataAndUpdateCharts();
  }
  
  // Função para lidar com a alteração na seleção
  function handleSelectChange() {
    var select = document.querySelector('select[name="botoes"]');
    var selectedOption = select.options[select.selectedIndex].value;
  
    if (selectedOption === 'handletxt') {
      fetchDataAndUpdateCharts();
    } else if (selectedOption === 'handlecsv') {
      // Lógica para lidar com arquivo CSV
    }
  }
  
  document.addEventListener('DOMContentLoaded', function() {
    // Resto do seu código...
  
    // Inicialize os gráficos
    var idadeChart = new Chart(document.getElementById('idade-chart'), {
      // Configurações do gráfico de idade...
    });
  
    var generoChart = new Chart(document.getElementById('genero-chart'), {
      // Configurações do gráfico de gênero...
    });
  
    var avaliacaoChart = new Chart(document.getElementById('avaliacao-chart'), {
      // Configurações do gráfico de avaliação...
    });
  });
  