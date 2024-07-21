import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import { Theme, presetGpnDefault } from '@consta/uikit/Theme';
import Home from './components/pages/home/Home';
import Layout from './components/navbar/Layout';
import LotPage from './components/pages/lot/LotPage';
import CustomerPage from './components/pages/customer/CustomerPage';

function App() {
  return (
    <Theme preset={presetGpnDefault}>
      <Router>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="/list/Customer" element={<CustomerPage />} />
            <Route path="/list/Lot" element={<LotPage />} />
          </Route>
        </Routes>
      </Router>
    </Theme>
  );
}

export default App;
