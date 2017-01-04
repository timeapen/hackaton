class RadarController {
  /** @ngInject */
  constructor($http, $log) {
    $log.info('RADAR');

    $http
      .get('app/charts/radar.json')
      .then(response => {
        this.chartData = response.data;

        const gaiaIndices = ["Quality", "Efficiency", "Satisfaction", "Value", "Cost"];
        this.chartData["scale-k"].values = gaiaIndices;

        const gaiaIndexRatings = ["Poor", "Fair", "Good", "Very Good", "Excellent"];
        this.chartData["scale-v"].values = gaiaIndexRatings;
      });
  }
}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
