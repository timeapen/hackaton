import angular from 'angular';

import chartData from '../services/chartdata.service.js';

import {pie} from './pie';
import {radar} from './radar';

export const chartsModule = 'charts';

angular
  .module(chartsModule, ['zingchart-angularjs', chartData])
  .component('pie', pie)
  .component('radar', radar);
