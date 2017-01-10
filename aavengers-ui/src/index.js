import angular from 'angular';

import 'angular-ui-router';

import 'angular-animate';
import 'angular-aria';
import 'angular-material';

import 'zingchart';
import 'zingchart-angularjs';

import routesConfig from './routes';

import {main} from './app/main';
import {header} from './app/header';
import {footer} from './app/footer';

import {chartsModule} from './app/charts/index';

import './index.scss';
import './angular-material.min.css';

angular
  .module('app', [chartsModule, 'ui.router', 'ngMaterial', 'zingchart-angularjs'])
  .config(routesConfig)
  .component('app', main)
  .component('fountainHeader', header)
  .component('fountainFooter', footer);
