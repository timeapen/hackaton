class RadarController {
  /** @ngInject */
  constructor($log) {
    $log.info('RADAR');
  }
}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
