import angular from 'angular';

import {pie} from './pie';
import {radar} from './radar';

export const mapsModule = 'maps';

angular
  .module(mapsModule, [])
  .component('pie', pie)
  .component('radar', radar);
