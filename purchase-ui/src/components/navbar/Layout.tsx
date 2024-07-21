import { Outlet } from 'react-router-dom';
import Header from './Header';
import './Layout.css';
function Layout() {
  return (
    <div className="layout">
      <Header />
      <Outlet />
    </div>
  );
}

export default Layout;
