import React from 'react';

import 'bootswatch/dist/pulse/bootstrap.css'

import Navbar from './components/navbar'
import CadastroProspecto from './views/cadastroprospecto'



class App extends React.Component {

  render(){
    return(
      <div>
        <CadastroProspecto></CadastroProspecto>

      </div>

    );
  }

}

export default App;
