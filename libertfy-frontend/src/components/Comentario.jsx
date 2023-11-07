import React, { useState } from 'react';
import userImage from '../assets/user.png';
import { FaTrash, FaEdit } from 'react-icons/fa';
import './Comentario.scss';

export default function Comentario({ id, nome, voto, comentario, data, onExcluirComentario, onEditarComentario }) {
    const [editMode, setEditMode] = useState(false);
    const [editedComentario, setEditedComentario] = useState(comentario);

    const handleExcluir = () => {
        onExcluirComentario(id);
    };

    const handleEditar = () => {
        setEditMode(true);
    };

    const handleCancelarEdicao = () => {
        setEditMode(false);
        setEditedComentario(comentario);
    };

    const handleSalvarEdicao = () => {
        onEditarComentario(id, editedComentario);
        setEditMode(false);
    };

    const handleComentarioChange = e => {
        setEditedComentario(e.target.value);
    };

    return (
        <div className='containerComentario'>
            <div className="user">
                <img src={userImage} alt="" />
                <div className="text">
                    <div className="headerComentario">
                        <div className="infoUsers">
                            <span className="user">{nome}</span>
                            <div className="voto">{voto} estrelas</div>
                        </div>
                        <div className="acoes">
                            {!editMode && (
                                <>
                                    <button className="btnAcoes" onClick={handleEditar}>
                                        <FaEdit />
                                    </button>
                                    <button className="btnAcoes" onClick={handleExcluir}>
                                        <FaTrash />
                                    </button>
                                </>
                            )}

                            {editMode && (
                                <>
                                    <button className="btnCancelar" onClick={handleCancelarEdicao}>
                                        Cancelar
                                    </button>
                                    <button className="btnSalvar" onClick={handleSalvarEdicao}>
                                        Salvar
                                    </button>
                                </>
                            )}
                        </div>
                    </div>
                    {!editMode ? (
                        <div className="comentario">{comentario}</div>
                    ) : (
                        <textarea
                            autoComplete="off"
                            rows="1"
                            maxLength="2000"
                            value={editedComentario}
                            onChange={handleComentarioChange}
                        />
                    )}
                </div>
            </div>

            <div className="data">{data}</div>


        </div>
    );
}