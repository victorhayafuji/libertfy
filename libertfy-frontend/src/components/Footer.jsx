import React from 'react'
import './Footer.scss'

import ImgLogo from '../assets/libertify-white-red.png'

import { SiTwitter, SiInstagram, SiTiktok } from "react-icons/si";

import {} from 'react-icons'

export default function Footer() {
  return (

    <div className='footer'>
        <div className="custom-shape-divider-top-1681169232">
            <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                <path d="M985.66,92.83C906.67,72,823.78,31,743.84,14.19c-82.26-17.34-168.06-16.33-250.45.39-57.84,11.73-114,31.07-172,41.86A600.21,600.21,0,0,1,0,27.35V120H1200V95.8C1132.19,118.92,1055.71,111.31,985.66,92.83Z" className="shape-fill"></path>
            </svg>
        </div>
        <div className="logo">
            <img src={ImgLogo} alt="" />
        </div>

        <div className="itens">
            <div className="text">
            A Libertfy é uma plataforma online dedicada a divulgar os melhores restaurantes e suas culinárias na região da Liberdade. Com perfis detalhados de restaurantes, avaliações de usuários e informações úteis, a Libertfy é o seu guia confiável para explorar e desfrutar da diversidade culinária desse bairro icônico. Descubra sabores autênticos, encontre recomendações confiáveis e mergulhe em uma jornada gastronômica emocionante na Liberdade através da Libertfy.
            </div>
            <div className="topics">
                <div className="topics-itens">
                    <div>
                        <span>Pratos</span>
                        <ul>
                            <li>
                                Sushi
                            </li>
                            <li>
                                Ramen
                            </li>
                            <li>
                                Biryani
                            </li>
                            <li>
                                Bulgogi
                            </li>
                            <li>
                                Pad Thai
                            </li>
                        </ul>
                    </div>
                    <div>
                        <span>Restaurantes</span>
                        <ul>
                            <li>
                                Contatos
                            </li>
                            <li>
                                Pedidos
                            </li>
                        </ul>
                    </div>
                    <div>
                        <span>Sobre nós</span>
                        <ul>
                            <li>
                                Origem
                            </li>
                            <li>
                                Futuro
                            </li>
                            <li>
                                Shopping
                            </li>
                            <li>
                                Lorem Ipsum
                            </li>
                        </ul>
                    </div>
                </div>
                <div className="social-media-icons">
                    <SiTwitter className='icon' />
                    <SiInstagram className='icon' />
                    <SiTiktok className='icon' />
                </div>
            </div>
        </div>

        <div className="mark"></div>

        <div className="tag">
            <span>Projeto Acadêmico | SPTech - 2023</span>
        </div>

    </div>
  )
}
