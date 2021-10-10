import { Injectable } from '@angular/core';
import {Oportunidade} from './painel-negociacao/oportunidade';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class OportunidadeService {

  private serviceUrl = 'http://localhost:8080/oportunidades';

  constructor(private http: HttpClient) {
   }

   listar (){
       return this.http.get(this.serviceUrl);
  }

  inserir(oportunidade : any) {
    return this.http.post(this.serviceUrl, oportunidade);
  }
  
  excluir(id : number ){
    return this.http.delete<any>(this.serviceUrl + '/' + id);
  }


}


