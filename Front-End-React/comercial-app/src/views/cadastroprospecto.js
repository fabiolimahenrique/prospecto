import React from 'react';
import axios from 'axios'
import ProspectoTable from '../views/prospectoTable'

class CadastroProspecto extends React.Component {

   state = {
      nomeProspecto: null,
      descricao: null,
      tipoOferta: null,
      ano: null,
      etapa: null,
      empresa: null,
      setor: null,
      valor: null,
      prospectos: []
   }


   buscar = () => {
      axios.get('http://localhost:8080/oportunidades', {
      }).then(response => {
         console.log(response.data)
         const lista = response.data;
         this.setState({ prospectos: lista })
      }).catch(erro => {
         console.log(erro.response.data)
      })

   }

   downloadapi = (id) => {
      window.location.href = `http://localhost:8080/oportunidades/downloadporid/${id}/db`;
   }
   excluir = (id) => {
      axios.delete(`http://localhost:8080/oportunidades/${id}`)
         .then(response => {
            this.buscar();
            console.log(response.data);
         })
   }

   salvar = () => {

      axios.post('http://localhost:8080/oportunidades', {
         nomeProspecto: this.state.nomeProspecto,
         descricao: this.state.descricao,
         tipoOferta: this.state.tipoOferta,
         ano: this.state.ano,
         etapa: this.state.etapa,
         empresa: this.state.empresa,
         setor: this.state.setor,
         valor: this.state.valor,
      }).then(response => {
         console.log(response.data)
         this.buscar();
      }).catch(erro => {
         console.log(erro.response.data)
      })

   }


   handleChange = (event) => {
      const value = event.target.value;
      const name = event.target.name;
      this.setState({ [name]: value })
   }

   render() {
      return (
         <>
         <h1>Cadastro de Oportunidades </h1>
         <div class="jumbotron">
            <div className="row" >
               <div className="col-md-6" >
                  <div class="form-group">
                     <label for="inputNomeProspecto"> Prospecto </label>
                     <input id="inputNomeProspecto"
                        type="text"
                        name="nomeProspecto"
                        value={this.state.nomeProspecto}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter prospecto" />


                     <label for="inputDescricao"> Descrição </label>
                     <input id="inputDescricao"
                        type="text"
                        name="descricao"
                        value={this.state.descricao}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter descrição" />

                     <label for="inputTipoOferta"> Tipo de Oferta </label>
                     <input id="tipoOferta"
                        type="text"
                        name="tipoOferta"
                        value={this.state.tipoOferta}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter tipo da oferta" />

                     <label for="inputAno"> Ano </label>
                     <input id="inputAno"
                        type="text"
                        name="ano"
                        value={this.state.ano}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter ano" />

                     <label for="inputetapa"> Etapa </label>
                     <input id="inputetapa"
                        type="text"
                        name="etapa"
                        value={this.state.etapa}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter etapa" />

                     <label for="inputempresa"> Empresa </label>
                     <input id="inputempresa"
                        type="text"
                        name="empresa"
                        value={this.state.empresa}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter empresa" />

                     <label for="inputSetor"> Setor </label>
                     <input id="inputSetor"
                        type="text"
                        name="setor"
                        value={this.state.setor}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter setor" />


                     <label for="inputValor"> Valor </label>
                     <input id="inputValor"
                        type="text"
                        name="valor"
                        value={this.state.valor}
                        onChange={this.handleChange}
                        className="form-control"
                        placeholder="Enter Valor" />

                  </div>
               </div>
            </div>

            <div className="row" >
               <div className="col-md-6">

                  <button type="button" onClick={this.salvar} className="btn btn-success">
                     <i className="pi pi-times" />  Salvar
                  </button>

                  <button type="button" onClick={this.buscar} className="btn btn-primary">
                     <i className="pi pi-times" />  Buscar
                  </button>

               </div>
            </div>

            <div className="row" >
               <div className="col-md-12">
                  <div className="bs-component">
                     <ProspectoTable
                        prospectos={this.state.prospectos}
                        downloadAction={this.downloadapi}
                        deleteAction={this.excluir}
                     />

                  </div>

               </div>
            </div>


         </div>
         </>
      );
   }

}

export default CadastroProspecto