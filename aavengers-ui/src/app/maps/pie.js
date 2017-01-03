class PieController {
  /** @ngInject */
  constructor($http, $log) {
    $log.info('PIE');
    $http
      .get('app/maps/pie.json')
      .then(response => {
        this.techs = response.data;
      });
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};

