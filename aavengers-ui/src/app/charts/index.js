import angular from 'angular';

import {pie} from './pie';
import {radar} from './radar';

export const chartsModule = 'charts';

angular
  .module(chartsModule, ['zingchart-angularjs'])
  .component('pie', pie)
  .component('radar', radar);
