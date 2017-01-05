// Constants should be eventually pulled out into configuration data, retrieved from the server
const accountId = '140735003';
const indicator = 'Corruption';

class PieController {

  /** @ngInject */
  constructor($http, $log, indicators) {
    $log.info('PIE Controller');
    indicators.getPieChartIndicators(accountId, indicator)
     .then(response => {
       this.chartData = response;
     });
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};
