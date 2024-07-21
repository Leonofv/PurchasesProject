import { useState, useRef } from 'react';
import { Button } from '@consta/uikit/Button';
import { ContextMenu } from '@consta/uikit/ContextMenu';
import { useNavigate } from 'react-router-dom';
import { IconArrowDown } from '@consta/icons/IconArrowDown';

type Item = {
  label: string;
};

const items: Item[] = [
  {
    label: 'Заказчики',
  },
  {
    label: 'Лоты',
  },
];

function ContextMenuz(): JSX.Element {
  const navigate = useNavigate();

  const getItemOnClick = (item: Item) => {
    let path: string | undefined;

    if (item.label === 'Заказчики') {
      path = 'Customer';
    } else if (item.label === 'Лоты') {
      path = 'Lot';
    }

    if (path) {
      return () => navigate(`/list/${path}`);
    }
  };

  const ref = useRef(null);
  const [isOpen, setIsOpen] = useState<boolean>(false);

  return (
    <>
      <Button
        iconRight={IconArrowDown}
        ref={ref}
        label="Таблицы"
        onClick={() => setIsOpen(!isOpen)}
      />
      <ContextMenu
        role="menu"
        size="m"
        items={items}
        isOpen={isOpen}
        anchorRef={ref}
        onClickOutside={() => setIsOpen(false)}
        getItemOnClick={getItemOnClick}
      />
    </>
  );
}

export default ContextMenuz;
