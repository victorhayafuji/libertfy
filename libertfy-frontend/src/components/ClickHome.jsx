import React from 'react'
import { Link } from 'react-router-dom'
import logoVetor from '../assets/vector-logo.png'

export default function ClickHome() {
  return (
    <Link to={"/"}>
        <img src={logoVetor} alt="" />
    </Link>
  )
}
