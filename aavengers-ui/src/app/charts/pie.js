class PieController {

  /** @ngInject */
  constructor($http, $log, indicators) {
    $log.info('PIE Controller');
    indicators.getPieChartIndicators()
     .then(response => {
       $log.info("Retrieved chart data response: ", response);
       this.chartData = response;
     });
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};

