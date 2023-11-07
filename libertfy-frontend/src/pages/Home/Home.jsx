import React from 'react'
import Navbar from '../../components/Navbar'
import './Home.scss'
import ImgLogo from '../../assets/noodle-box-logo.png'

import logoImg from '../../assets/japan-chef-logo-libert.png'
import descLogo from '../../assets/desc-logo.png'

import Rest1 from '../../assets/restaurante1.png'
import Rest2 from '../../assets/restaurante2.jpg'
import Rest3 from '../../assets/restaurante3.jpg'
import Rest4 from '../../assets/restaurante4.jpg'
import Footer from '../../components/Footer'
import Carousel from '../../components/Carousel'
import { Link } from 'react-router-dom'



export default function Home() {

  const redirecionamento = () => {
    <Link to={"/restaurante"}></Link>
  }
  
  return (
    <div className='home'>
      <Navbar />

      <div className="libertfy">
        <div className="img">
          <img src={logoImg} alt="" />
        </div>

        {/* <div className="textMessage">
          <span>Encontre o caminho da sua fome!</span>
        </div> */}

        

      </div>




      <div className="welcome">
        {/* <div className="custom-shape-divider-top-1681176371">
          <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
            <path d="M985.66,92.83C906.67,72,823.78,31,743.84,14.19c-82.26-17.34-168.06-16.33-250.45.39-57.84,11.73-114,31.07-172,41.86A600.21,600.21,0,0,1,0,27.35V120H1200V95.8C1132.19,118.92,1055.71,111.31,985.66,92.83Z" className="shape-fill"></path>
          </svg>
        </div> */}
        <div className="text">
          <h1>Bem-vindo ao Libertfy</h1>
          <h2>conectamos você à Liberdade</h2>
        </div>
        <div className="content">
          <div className="text">
            <p>
            O bairro da Liberdade em São Paulo é um ponto turístico imperdível para aqueles que buscam explorar a vibrante cultura asiática. Com a maior comunidade japonesa fora do Japão, o bairro oferece uma grande variedade de restaurantes japoneses que servem desde sushis e sashimis fresquinhos até ramens reconfortantes. Além disso, é possível experimentar pratos autênticos chineses, coreanos, taiwaneses e tailandeses, o que torna o bairro uma verdadeira meca culinária. Embora seja comum encontrar filas de espera nos restaurantes nos finais de semana e feriados, a espera vale a pena para desfrutar de uma jornada culinária inesquecível e conhecer mais sobre a cultura asiática presente no bairro da Liberdade.
            </p>

            <button onClick={redirecionamento}>Conhecer restaurantes</button>
          </div>
          <div className="imgs">
            <img src={Rest1} alt="restaurante 1" />
            <img src={Rest2} alt="restaurante 2" />
            <img src={Rest3} alt="restaurante 3" />
            <img src={Rest4} alt="restaurante 4" />
          </div>
        </div>
      </div>
      <Carousel />

      <Footer />

    </div>
  )
}
