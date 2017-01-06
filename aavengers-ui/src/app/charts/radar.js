const accountId = '92692004|000133190USD|000100292HKD|000133077CHF|000133468EUR|000100675PHP|000667640GBP';

class RadarController {
  /** @ngInject */
  constructor($http, $log, chartData) {
    $log.info('RADAR Controller');

    chartData.createRadarChartIndicators(accountId)
     .then(response => {
       this.chartData = response;
     });
  }

}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
