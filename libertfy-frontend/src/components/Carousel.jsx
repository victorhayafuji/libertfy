import React, { useState, useRef, useEffect } from 'react'
import Slider1 from '../assets/tranding-food-1.png'
import Slider2 from '../assets/tranding-food-2.png'
import Slider3 from '../assets/tranding-food-3.png'

import { Link } from 'react-router-dom'

import { restaurantes } from '../Static'

import './Carousel.scss'

export default function Carousel() {

  const carousel = useRef(null)

  const handleLeftClick = (e) => {
    e.preventDefault();
    carousel.current.scrollLeft -= carousel.current.offsetWidth;
  }

  const handleRightClick = (e) => {
    e.preventDefault();
    carousel.current.scrollLeft += carousel.current.offsetWidth;
  }

  return (
    <div className='container-carousel'>
      <div className="heading">
        <h1>Várias opções</h1>
        <h2>em um só lugar</h2>
      </div>
      <div className='carousel'>

      <button onClick={handleLeftClick} className='left-button'><ion-icon name="arrow-back-outline"></ion-icon></button>

        <div className="itens" ref={carousel}>
          {restaurantes.map((restaurante) => {
            return (
              <div className="item" key={restaurante.id}>
                <div className="image">
                  <img src={restaurante.image} alt={restaurante.name} />
                </div>
                <div className="info">
                  <span className="name">{restaurante.name}</span>
                  <span className="type">
                    {restaurante.type}
                  </span>
                  <Link to={"/restaurante"} className="btn">Ver restaurante</Link>
                </div>
              </div>
            );
          })}
        </div>
        
          
        <button onClick={handleRightClick} className='right-button'><ion-icon name="arrow-forward-outline"></ion-icon></button>
        

      </div>
    </div>
  )
}
