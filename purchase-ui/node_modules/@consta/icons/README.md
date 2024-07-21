# [Дизайн-система Consta](http://consta.design/) | Библиотека иконок

Consta — дизайн-система для разработки интерфейсов, написана на [React](https://reactjs.org/). В этой библиотеке собраны все иконки, которые используются в дизайн-системе.

# Как использовать

## Установите пакет

`yarn add @consta/uikit @consta/icons`

## Подключите тему

Чтобы начать работу с библиотекой интерфейсных компонентов, подключите [тему](https://consta.design/libs/portal/theme-themeabout).

## Можно использовать компоненты

Например, так:

```tsx
import React from 'react';
import { Theme, presetGpnDefault } from '@consta/uikit/Theme';
import { Button } from '@consta/uikit/Button';
import { IconCancel } from '@consta/icons/IconCancel';

const App = () => (
  <Theme preset={presetGpnDefault}>
    <Button label="Закрыть" iconLeft={IconCancel} onlyIcon />
  </Theme>
);
```

## Документация и стенд

На стенде можно менять параметры и смотреть, как меняются компоненты. Документация — во вкладке у каждого компонента.

[Вперёд, к стенду](https://consta.design/libs/icons)

## Разработка

### Подготовка окружения

Рабочее окружение должно содержать NodeJS и Yarn.

Чтобы установить зависимости, выполните команду:

```sh
$ yarn install
```

### Основные команды

```sh
# Запуск локального сервера для разработки
$ yarn start

# Сборка пакета
$ yarn build

# Сборка стенда
$ yarn stand:build

# Запуск тестов
$ yarn test

# Преобразование иконок в компонеты
$ yarn icon-generate
```

## Контрибьюторам

Будем рады, если вы захотите принять участие в разработке дизайн-системы =) Но сначала прочитайте [инструкцию для контрибьюторов](https://consta.design/libs/portal/contributers-code).

## Лицензия

Дизайн-систему можно использовать бесплатно, она распространяется на условиях открытой [лицензии MIT](https://consta.design/static/licence_mit.pdf).
