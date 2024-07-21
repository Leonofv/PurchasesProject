import { Button } from '@consta/uikit/Button';
import { IconHome } from '@consta/icons/IconHome';
import { Link } from 'react-router-dom';
import ContextMenuz from './ContextMenu';

function Header(): JSX.Element {
  return (
    <header>
      <div className="container">
        <Link to="/">
          <Button label="Главная" iconRight={IconHome} />
        </Link>
        <ContextMenuz />
      </div>
    </header>
  );
}

export default Header;
