import React from 'react';
import { Admin, Resource } from 'react-admin';
import i18nProvider from './i18n';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import NotFound from './pages/NotFound';
import Users from './pages/Users';
import Expenses  from './pages/Expenses'; 
import Revenues  from './pages/Revenues'; 
import authProvider from './providers/authProvider';
import dataProvider from './providers/dataProvider';
import './styles/App.css';
import theme from './themes/theme';

const fetchResources = permissions => {
  let arr = [];
  arr.push(<Resource name="home" {...HomePage} />);
  arr.push(<Resource name="users" {...Users} />);
  arr.push(<Resource name="expenses" {...Expenses} />);
  arr.push(<Resource name="revenues" {...Revenues} />);
  return arr;
};

function App() {
  return (
    <div className="App">
      <Admin
        theme={theme}
        i18nProvider={i18nProvider}
        // authProvider={authProvider}
        dataProvider={dataProvider}
        // loginPage={LoginPage}
        catchAll={NotFound}
      >
        {fetchResources}
      </Admin>
    </div>
  );
}

export default App;
