import angular from 'angular';

import indicators from '../services/indicators.service.js';
import chartData from '../services/chartdata.service.js';

import {pie} from './pie';
import {radar} from './radar';

export const chartsModule = 'charts';

angular
  .module(chartsModule, ['zingchart-angularjs', indicators, chartData])
  .component('pie', pie)
  .component('radar', radar);
