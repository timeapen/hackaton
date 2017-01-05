class RadarController {
  /** @ngInject */
  constructor($http, $log, indicators) {
    $log.info('RADAR Controller');

    indicators.getRadarChartIndicators()
     .then(response => {
       this.chartData = response;
     });
  }

}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
