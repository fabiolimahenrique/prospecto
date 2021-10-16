import React from 'react'


export default (props) => {

    const rows = props.prospectos.map( prospecto => {
         return(
             <tr key={prospecto.id} >
               <td>{prospecto.nomeProspecto}</td>
               <td>{prospecto.descricao}</td>
               <td>{prospecto.tipoOferta}</td>
               <td>{prospecto.ano}</td>
               <td>{prospecto.etapa}</td>
               <td>{prospecto.empresa}</td>
               <td>{prospecto.setor}</td>
               <td>{prospecto.valor}</td>
               <td>
                  <button type="button" 
                          onClick={e => props.deleteAction(prospecto.id)}
                          className="btn btn-danger">
                              <i className="pi pi-times"></i>
                              excluir
                  </button>
               </td>
            </tr>    
         ) 
    })

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Nome Prospecto</th>
                    <th scope="col">Descrição</th>
                    <th scope="col">tipoOferta</th>
                    <th scope="col">ano</th>
                    <th scope="col">etapa</th>
                    <th scope="col">empresa</th>
                    <th scope="col">setor</th>
                    <th scope="col">Valor</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead> 
            <tbody>
                {rows}
            </tbody>

        </table>
    )
}