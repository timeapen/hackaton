import angular from 'angular';

import indicators from '../services/indicators.service.js';

import {pie} from './pie';
import {radar} from './radar';

export const chartsModule = 'charts';

angular
  .module(chartsModule, ['zingchart-angularjs', indicators])
  .component('pie', pie)
  .component('radar', radar);
