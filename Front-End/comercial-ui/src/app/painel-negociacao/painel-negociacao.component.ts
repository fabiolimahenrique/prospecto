import { Component, OnInit } from '@angular/core';
import { OportunidadeService } from '../oportunidade.service';
import { MessageService } from 'primeng/api';
import { Oportunidade } from './oportunidade';



@Component({
  selector: 'app-painel-negociacao',
  templateUrl: './painel-negociacao.component.html',
  styleUrls: ['./painel-negociacao.component.css']
})
export class PainelNegociacaoComponent implements OnInit {
  
  oportunidade = {};
  oportunidades = [];

  constructor(
    private oportunidadeService: OportunidadeService,
    private messageService: MessageService
    ) 
  {  }

  ngOnInit() {

    this.consultar();
  }

consultar(){
  this.oportunidadeService.listar()
  .subscribe( resultado => this.oportunidades = <any> resultado) ;   

}

remover(oportunidade : Oportunidade){
  this.oportunidadeService.excluir(oportunidade.id)
    .subscribe(
        () => {
          this.consultar();  
        }
    );
}


adicionar(){
   this.oportunidadeService.inserir(this.oportunidade)
     .subscribe( () => {
       this.oportunidade = {};
       this.consultar();

      this.messageService.add({
        severity: 'success',
        summary: 'Oportunidade adicionada com sucesso' 
      }

      );

     },
        resposta => {
          let msg = 'Erro inesperado.';
          
          if (resposta.error.message){
            msg = resposta.error.message;
          }
          
          this.messageService.add({
            severity: 'error',
            summary: msg
          });
        }

     )
}


}
