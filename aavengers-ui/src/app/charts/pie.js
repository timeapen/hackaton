class PieController {
  /** @ngInject */
  constructor($http, $log) {
    $log.info('PIE');
    $http
      .get('app/charts/pie.json')
      .then(response => {
        this.chartData = response.data;
        $log.info(this.chartData);
      });
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};

